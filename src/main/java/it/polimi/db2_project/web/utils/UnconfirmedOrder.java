package it.polimi.db2_project.web.utils;

import it.polimi.db2_project.ejb.beans.OptionalProduct;
import it.polimi.db2_project.ejb.beans.ServicePackage;
import java.util.Date;
import java.util.List;

public class UnconfirmedOrder {
    private final ServicePackage servicePackage;
    private final List<OptionalProduct> optionalProductList;
    private final Date subscriptionDate;
    private double totalPrice;


    public UnconfirmedOrder(ServicePackage servicePackageID, List<OptionalProduct> optionalProductList, Date subscriptionDate) {
        this.servicePackage = servicePackageID;
        this.optionalProductList = optionalProductList;
        this.subscriptionDate = subscriptionDate;
    }

    public void computeTotalPrice(){
        this.totalPrice = (servicePackage.getMonthly_fee() * servicePackage.getValidity_period()) +
                (optionalProductList.stream().map(optionalProduct ->
                        optionalProduct.getMonthly_fee() * optionalProduct.getValidity_period())
                        .reduce(0.0, Double::sum));
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

    public double getTotalPrice() {
        return totalPrice;
    }
}
