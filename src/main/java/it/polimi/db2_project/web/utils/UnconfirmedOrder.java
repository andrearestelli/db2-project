package it.polimi.db2_project.web.utils;

import it.polimi.db2_project.ejb.beans.OptionalProduct;

import java.util.Date;
import java.util.List;

public class UnconfirmedOrder {
    private Integer servicePackageID;
    private List<OptionalProduct> optionalProductList;
    private Date subscriptionDate;

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
}
