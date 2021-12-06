package it.polimi.db2_project.web.utils;

import it.polimi.db2_project.ejb.beans.OptionalProduct;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UnconfirmedOrder {
    Integer servicePackageID;
    List<OptionalProduct> optionalProductList;
    Date subscriptionDate;

    public UnconfirmedOrder(Integer servicePackageID, List<OptionalProduct> optionalProductList, Date subscriptionDate) {
        this.servicePackageID = servicePackageID;
        this.optionalProductList = optionalProductList;
        this.subscriptionDate = subscriptionDate;
    }

    public Integer getServicePackageID() {
        return servicePackageID;
    }

    public List<OptionalProduct> getOptionalProductList() {
        return optionalProductList;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public void setServicePackageID(Integer servicePackageID) {
        this.servicePackageID = servicePackageID;
    }

    public void setOptionalProductList(List<OptionalProduct> optionalProductList) {
        this.optionalProductList = optionalProductList;
    }

    public Date computeEndingDate(Date subscriptionDate, int validity_period)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(subscriptionDate);
        calendar.add(Calendar.MONTH,validity_period);
        return calendar.getTime();
    }
}
