package it.polimi.db2_project.ejb.services;

import it.polimi.db2_project.ejb.beans.OptionalProduct;
import it.polimi.db2_project.ejb.beans.Service;
import it.polimi.db2_project.ejb.beans.ServicePackage;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PackageService {

    @PersistenceContext(name = "telcoEJB")
    private EntityManager em;

    public List<ServicePackage> findAllServicePackage(){
        return em.createNamedQuery("ServicePackage.findAllPackages", ServicePackage.class).
                                getResultList();
    }

    public ServicePackage findByID(Integer ID)
    {
        return em.find(ServicePackage.class,ID);
    }

    public void createServicePackage(String name, int validityPeriod, double monthlyFee,
                                     List<Service> services, List<OptionalProduct> optionalProducts){
        ServicePackage servicePackage = new ServicePackage(name, validityPeriod, monthlyFee, optionalProducts, services);
        // Persist to move the new created instance to the managed state
        // Flush to ask the Persistence provider to write changes as soon as possible to the database
        em.persist(servicePackage);
        em.flush();
    }
}
