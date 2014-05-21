package dssys_assignment1;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;

public class DsSys_Assignment1 {

    private final int port;
    private Selector selector;
    private final HashMap<SocketChannel, ArrayList<byte[]>> dataMap;
    private final ArrayList<ClientRequest> cliList = new ArrayList<>();
    private final String rnToHex = "000000000000000000000000000000000d0a2e0d";

    public DsSys_Assignment1(int port) throws IOException {
        this.port = port;
        dataMap = new HashMap<>();
        startServer();
    }

    private void startServer() throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);

        InetSocketAddress listenAddr = new InetSocketAddress(this.port);
        serverChannel.socket().bind(listenAddr);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Waiting for client on port " + port + "....");

        while (true) {
            selector.select();

            Iterator keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = (SelectionKey) keys.next();
                keys.remove();
                try {
                    if (key.isAcceptable()) {
                        this.accept(key);
                    }
                    if (key.isReadable()) {
                        System.out.println("Reading from channel");
                        this.read(key);
                    }
                    if (key.isWritable()) {
                        this.write(key);
                    }

                } catch (CancelledKeyException e) {
                    System.out.println("Message finished. Closing channel.");
                    key.channel().close();
                }
            }
        }
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel channel = serverChannel.accept();
        channel.configureBlocking(false);

        if (getClientByChannel(channel) == null) {
            int id = (int) (Math.random() * 9999);
            cliList.add(new ClientRequest(key, id, channel));
        }

        String initStr = ("220 Service Ready\r\n");
        System.out.println("Sent: " + initStr);
        channel.write(ByteBuffer.wrap(initStr.getBytes("US-ASCII")));

        Socket socket = channel.socket();
        SocketAddress remoteAddr = socket.getRemoteSocketAddress();
        String IP = remoteAddr.toString().split(":")[0];
        System.out.println("Connected to: " + IP);
        
        
        
        dataMap.put(channel, new ArrayList<byte[]>());
        channel.register(this.selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int numRead = -1;
        try {
            numRead = channel.read(buffer);
            buffer.flip();
            
        } catch (IOException e) {
            System.err.print("Issues in read function");
        }

        if (numRead == -1) {
            this.dataMap.remove(channel);
            SocketAddress remoteAddr = channel.socket().getRemoteSocketAddress();
            System.out.println("\nConnection closed by client: " + remoteAddr);
            channel.close();
            key.cancel();
            return;
        }

        byte[] data = Arrays.copyOf(buffer.array(), numRead);
        System.out.println("I received: " + new String(data, "US-ASCII"));
        String toSend;
        ClientRequest c = getClientByChannel(channel);
        if (c == null) {
            System.out.println("counldn't find client in list, something went wrong");
            System.exit(0);
        }

        if (c.isSentQuit()) {
            c.getCliChannel().close();
        }
        
        String bytesToString = new String(data, "US-ASCII").split(" ")[0].split("\n")[0].split("\r")[0];
        switch (bytesToString) {
            case "HELO":
                toSend = ("250 " + java.net.InetAddress.getLocalHost().getHostName() + " OK\r\n");
                ;
                c.setSentHelo(true);
                break;
            case "MAIL":
                toSend = ("250 OK\r\n");
                c.setSentMail(true);
                c.setFrom(new String(data, "US-ASCII").split(" ")[2]);
                break;
            case "RCPT":
                toSend = ("250 OK\r\n");
                c.setSentRcpt(true);
                c.setTo(new String(data, "US-ASCII").split(" ")[2]);
                break;
            case "DATA":
                System.out.println("Incoming data");
                toSend = ("354 OK\r\n");
                c.setSentData(true);
                break;
            case "QUIT":
                toSend = ("221 Bye\r\n");
                c.setSentQuit(true);
                break;
            case "HELP":
                System.out.println("Received HELP command");
                toSend = "214 Commands Supported: HELO MAIL RCPT DATA QUIT HELP\r\n";
                break;
            default:
                String lastChars = toHex(new String(data, "US-ASCII").substring(new String(data, "US-ASCII").length() - 5, new String(data, "US-ASCII").length() - 1));
                if (lastChars.equals(rnToHex)) {
                    toSend = ("250 OK\r\n");
                    c.setData(new String(data, "US-ASCII"));
                    c.setSentDataEnd(true);
                } else {
                    toSend = "Command not recognized\r\n";
                }
                break;

        }
        data = toSend.getBytes();
        System.out.println("I send " + toSend.replace("\r\n", "") + " :: " + data.length + " bytes sent");
        replyWithEcho(key, data);
    }

    private void write(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        List<byte[]> pendingData = this.dataMap.get(channel);
        Iterator<byte[]> items = pendingData.iterator();
        while (items.hasNext()) {
            byte[] item = items.next();
            items.remove();
            channel.write(ByteBuffer.wrap(item));
        }
        key.interestOps(SelectionKey.OP_READ);
    }

    public String toHex(String arg) {
        return String.format("%040x", new BigInteger(1, arg.getBytes()));
    }

    private void replyWithEcho(SelectionKey key, byte[] data) {
        SocketChannel channel = (SocketChannel) key.channel();
        List<byte[]> pendingData = this.dataMap.get(channel);
        pendingData.add(data);
        key.interestOps(SelectionKey.OP_WRITE);
    }

    private ClientRequest getClientByChannel(SocketChannel channel) {
        for (ClientRequest cliList1 : cliList) {
            if (cliList1 != null && cliList1.getCliChannel().equals(channel)) {
                return cliList1;
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        new DsSys_Assignment1(22011);
    }
}
