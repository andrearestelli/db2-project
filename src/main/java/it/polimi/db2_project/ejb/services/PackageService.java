package it.polimi.db2_project.ejb.services;

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
}
