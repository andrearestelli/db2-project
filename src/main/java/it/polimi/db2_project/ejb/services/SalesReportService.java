package it.polimi.db2_project.ejb.services;


import it.polimi.db2_project.ejb.beans.Alert;
import it.polimi.db2_project.ejb.beans.salesReportBeans.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SalesReportService {
    @PersistenceContext(name = "telcoEJB")
    private EntityManager em;

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
