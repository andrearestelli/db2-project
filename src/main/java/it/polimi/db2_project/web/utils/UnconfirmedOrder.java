package it.polimi.db2_project.web.utils;

import it.polimi.db2_project.ejb.beans.OptionalProduct;
import it.polimi.db2_project.ejb.beans.ServicePackage;
import java.util.Date;
import java.util.List;

public class UnconfirmedOrder {
    private ServicePackage servicePackage;
    List<OptionalProduct> optionalProductList;
    Date subscriptionDate;

    public UnconfirmedOrder(ServicePackage servicePackageID, List<OptionalProduct> optionalProductList, Date subscriptionDate) {
        this.servicePackage = servicePackageID;
        this.optionalProductList = optionalProductList;
        this.subscriptionDate = subscriptionDate;
    }

    public ServicePackage getServicePackage() {
        return servicePackage;
    }

    public List<OptionalProduct> getOptionalProductList() {
        return optionalProductList;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

}
