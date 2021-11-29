package it.polimi.db2_project.ejb.services;

import it.polimi.db2_project.ejb.beans.Service;
import it.polimi.db2_project.ejb.beans.ServicePackage;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PackageService {
   /* @PersistenceContext
    private EntityManager em;

    public List<ServicePackage> findAllServicePackage(){
        return em.createNamedQuery("ServicePackage.findAllPackages", ServicePackage.class).
                                getResultList();
    }
*/
}
