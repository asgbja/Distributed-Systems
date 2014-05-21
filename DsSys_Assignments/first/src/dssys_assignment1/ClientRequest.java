package dssys_assignment1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ClientRequest {

    private boolean sentHelo = false;
    private boolean sentMail = false;
    private boolean sentRcpt = false;
    private boolean sentData = false;
    private boolean sentDataEnd = false;
    private boolean sentQuit = false;

    private SocketChannel cliChannel;
    private SelectionKey key;

    private int id;
    private String from;
    private String to;
    private String data;

    public ClientRequest(SelectionKey key, int id, SocketChannel channel) {
        this.key = key;
        this.id = id;
        this.cliChannel = channel;
    }

    public boolean isSentHelo() {
        return sentHelo;
    }

    public void setSentHelo(boolean sentHelo) {
        this.sentHelo = sentHelo;
    }

    public boolean isSentMail() {
        return sentMail;
    }

    public void setSentMail(boolean sentMail) {
        this.sentMail = sentMail;
    }

    public boolean isSentRcpt() {
        return sentRcpt;
    }

    public void setSentRcpt(boolean sentRcpt) {
        this.sentRcpt = sentRcpt;
    }

    public boolean isSentData() {
        return sentData;
    }

    public void setSentData(boolean sentData) {
        this.sentData = sentData;
    }

    public boolean isSentDataEnd() {
        return sentDataEnd;
    }

    public void setSentDataEnd(boolean sentDataEnd) {
        this.sentDataEnd = sentDataEnd;
    }

    public boolean isSentQuit() {
        return sentQuit;
    }

    public void setSentQuit(boolean sentQuit) {
        if (sentQuit) {
            this.writeToFile();
        }
        this.sentQuit = sentQuit;
    }

    private void writeToFile() {
        System.out.println("\nwriting Mail data to file...\n");
        ByteBuffer byteBuffer;
        String messageToWrite;

        File dir = new File(this.getTo().replace("\r", "").replace("\n", ""));
        if (!dir.exists()) {
            dir.mkdir();
        }
        
        String fileName = this.getTo().replace("\r\n", "") + System.getProperty("file.separator") + this.getFrom().replace("\r\n", "") + "_" + this.getId() + ".txt";
        File file = new File(fileName);
        
        try (FileOutputStream fileOutputStream = new FileOutputStream(file, true)) {

            FileChannel fileChannel = fileOutputStream.getChannel();
            messageToWrite = this.getData();
            byteBuffer = ByteBuffer.wrap(messageToWrite.getBytes(Charset.forName("US-ASCII")));
            fileChannel.write(byteBuffer);

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public SelectionKey getKey() {
        return key;
    }

    public void setKey(SelectionKey key) {
        this.key = key;
    }

    public SocketChannel getCliChannel() {
        return cliChannel;
    }

    public void setCliChannel(SocketChannel cliChannel) {
        this.cliChannel = cliChannel;
    }

}
