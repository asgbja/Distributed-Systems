
package dssys_assignment2_client;


public class Result {

    private final String destination;    
    private final int price;
    private final int hiltonPrice;
    private final int holidayInnPrice ;
    
    public Result(String destination, int price, int hiltonPrice, int holidayInnPrice){
        this.destination = destination;
        this.price = price;
        this.hiltonPrice = hiltonPrice;
        this.holidayInnPrice = holidayInnPrice;
    }
    
    @Override
    public String toString(){
        if(price==-1){
            return "SEARCH ERROR: No flights available for " + destination;
        }
        return "Destination: " + destination + "\n\nCheapest Flight: " + price +
                "\n\nPrice in Hilton Hotel: " + hiltonPrice + "\n\nPrice in Holiday Inn: "+
                holidayInnPrice;
    }
}
