package it.polimi.db2_project.web.utils;

import it.polimi.db2_project.ejb.beans.OptionalProduct;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class RawUnconfirmedOrder {
    Integer servicePackageID;
    List<Integer> optionalProductList;
    String subscriptionDate;

    public RawUnconfirmedOrder(Integer servicePackageID, List<Integer> optionalProductList, String subscriptionDate) {
        this.servicePackageID = servicePackageID;
        this.optionalProductList = optionalProductList;
        this.subscriptionDate = subscriptionDate;
    }

    public Integer getServicePackageID() {
        return servicePackageID;
    }

    public List<Integer> getOptionalProductList() {
        return optionalProductList;
    }

    public String getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setServicePackageID(Integer servicePackageID) {
        this.servicePackageID = servicePackageID;
    }

    public void setOptionalProductList(List<Integer> optionalProductList) {
        this.optionalProductList = optionalProductList;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Date formatDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }
}
