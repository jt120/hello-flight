package com.jt.web.model;

import java.util.Date;

/**
 * Created by he on 2015/11/1.
 */
public class SearchResult {

    private String carrier;
    private String flightNumber;
    private Date depTime;
    private Date arrTime;

    private int adultPrice;
    private int adultTax;
    private int policyId;

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getDepTime() {
        return depTime;
    }

    public void setDepTime(Date depTime) {
        this.depTime = depTime;
    }

    public Date getArrTime() {
        return arrTime;
    }

    public void setArrTime(Date arrTime) {
        this.arrTime = arrTime;
    }

    public int getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public int getAdultTax() {
        return adultTax;
    }

    public void setAdultTax(int adultTax) {
        this.adultTax = adultTax;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SearchResult{");
        sb.append("carrier='").append(carrier).append('\'');
        sb.append(", flightNumber='").append(flightNumber).append('\'');
        sb.append(", depTime=").append(depTime);
        sb.append(", arrTime=").append(arrTime);
        sb.append(", adultPrice=").append(adultPrice);
        sb.append(", adultTax=").append(adultTax);
        sb.append(", policyId=").append(policyId);
        sb.append('}');
        return sb.toString();
    }
}
