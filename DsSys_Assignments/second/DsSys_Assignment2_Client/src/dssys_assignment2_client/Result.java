package dssys_assignment2_client;

public class Result {

    private final String destination;
    private final int price;
    private final int hiltonPrice;
    private final int holidayInnPrice;
    private final int mercurePrice;
    private final int carSixtPrice;

    public Result(String destination, int price, int hiltonPrice, int holidayInnPrice,
            int mercurePrice, int carSixtPrice) {
        this.destination = destination;
        this.price = price;
        this.hiltonPrice = hiltonPrice;
        this.holidayInnPrice = holidayInnPrice;
        this.mercurePrice = mercurePrice;
        this.carSixtPrice = carSixtPrice;
    }

    @Override
    public String toString() {
        if (price == -1) {
            return "SEARCH ERROR: No flights available for " + destination;
        }
        getMinimumPrice();
        return "Destination: " + destination + "\n\nCheapest Flight: " + price
                + "\n\nCheapest Hotel: " + getMinimumPrice() +
                "\n\nCar Sixt Price: " + carSixtPrice;
    }

    private String getMinimumPrice() {
        if(hiltonPrice!=-1 && hiltonPrice <= holidayInnPrice && hiltonPrice <= mercurePrice)
            return "Hilton Hotel for " + hiltonPrice + " €";
        if(holidayInnPrice!=-1 && holidayInnPrice<=hiltonPrice && holidayInnPrice <= mercurePrice)
            return "Hotel Inn for " + holidayInnPrice + " €";
        if(mercurePrice!=-1 && mercurePrice<=hiltonPrice && mercurePrice<=holidayInnPrice)
            return "Mercure Hotel for " + mercurePrice + " €";
        return "No hotel found";
    }
}
