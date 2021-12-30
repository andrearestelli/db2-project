package it.polimi.db2_project.ejb.services;


import it.polimi.db2_project.ejb.beans.Alert;
import it.polimi.db2_project.ejb.beans.Customer;
import it.polimi.db2_project.ejb.beans.Order;
import it.polimi.db2_project.ejb.beans.salesreportbeans.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SalesReportService {
    @PersistenceContext(name = "telcoEJB")
    private EntityManager em;

    public List<Alert> findAllAlerts(){
        return em.createNamedQuery("Alert.findAllAlerts", Alert.class).getResultList();
    }

    public List<Order> findAllRejectedOrders(){
        return em.createNamedQuery("Order.findAllRejectedOrders", Order.class).getResultList();
    }

    public List<Customer> findInsolventCustomers(){
        return em.createNamedQuery("Customer.findInsolventCustomers", Customer.class).getResultList();
    }

    public List<AverageNumberOfOptionalProducts> retrieveAllAverage(){
        return em.createNamedQuery("AverageNumberOfOptionalProducts.retrieveAll",
                AverageNumberOfOptionalProducts.class).getResultList();
    }

    public List<TotalPurchasesPerPackage> retrieveAllTotalPurchasesPerPackage(){
        return em.createNamedQuery("TotalPurchasesPerPackage.retrieveAll",
                TotalPurchasesPerPackage.class).getResultList();
    }

    public List<TotalPurchasesValidityPeriodPerPackage> retrieveAllTotalPurchasesValidityPeriod(){
        return em.createNamedQuery("TotalPurchasesValidityPeriodPerPackage.retrieveAll",
                TotalPurchasesValidityPeriodPerPackage.class).getResultList();
    }

    public List<TotalValueOfSales> retrieveAllTotalValueOfSales(){
        return em.createNamedQuery("TotalValueOfSales.retrieveAll",
                TotalValueOfSales.class).getResultList();
    }

    public BestSellerOptionalProduct retrieveBestSellerOptProduct(){
        List<BestSellerOptionalProduct> bestSellerOptionalProducts = em.createNamedQuery("BestSellerOptionalProduct.retrieveBestSeller",
                BestSellerOptionalProduct.class).setMaxResults(1).getResultList();
        if(bestSellerOptionalProducts.isEmpty())
            return null;
        else return bestSellerOptionalProducts.get(0);
    }
}
