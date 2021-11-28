package it.polimi.db2_project.ejb.services;

import it.polimi.db2_project.ejb.beans.Customer;
import it.polimi.db2_project.ejb.beans.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Stateless
public class EmployeeService {

    @PersistenceContext
    private EntityManager em;

    public Optional<Employee> checkCredentials(String username, String password) {
        return em.createNamedQuery("Employee.checkCredentials",Employee.class).
                setParameter("username",username).setParameter("password",password).
                getResultStream().findFirst();
    }
}
