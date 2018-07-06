package paradiseTravels.service.offer;

import java.util.Date;

public class OfferBuyRequestModel {
    private int[] localJourneyIds;
    private int offerId;
    private Date from;
    private Date to;
    private int numberOfCustomers;
    private int numberOfOnePersonBed;
    private int numberOfTwoPersonBed;

    public int[] getLocalJourneyIds() {
        return localJourneyIds;
    }

    public void setLocalJourneyIds(int[] localJourneyIds) {
        this.localJourneyIds = localJourneyIds;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    public int getNumberOfOnePersonBed() {
        return numberOfOnePersonBed;
    }

    public void setNumberOfOnePersonBed(int numberOfOnePersonBed) {
        this.numberOfOnePersonBed = numberOfOnePersonBed;
    }

    public int getNumberOfTwoPersonBed() {
        return numberOfTwoPersonBed;
    }

    public void setNumberOfTwoPersonBed(int numberOfTwoPersonBed) {
        this.numberOfTwoPersonBed = numberOfTwoPersonBed;
    }
}
