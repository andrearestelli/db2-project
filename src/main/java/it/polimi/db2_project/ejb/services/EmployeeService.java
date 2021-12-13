package it.polimi.db2_project.ejb.services;

import it.polimi.db2_project.ejb.beans.Employee;
import it.polimi.db2_project.ejb.beans.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class EmployeeService {

    @PersistenceContext(name = "telcoEJB")
    private EntityManager em;

    public Optional<Employee> checkCredentials(String username, String password) {
        return em.createNamedQuery("Employee.checkCredentials",Employee.class).
                setParameter("username",username).setParameter("password",password).
                getResultStream().findFirst();
    }

    public List<Service> findAllServices()
    {
        return em.createNamedQuery("Service.findAllServices",Service.class).getResultList();
    }
}
