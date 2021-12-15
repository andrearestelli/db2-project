package it.polimi.db2_project.ejb.services;

import it.polimi.db2_project.ejb.beans.Employee;
import it.polimi.db2_project.ejb.beans.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class EmployeeService {

    @PersistenceContext(name = "telcoEJB")
    private EntityManager em;

    public Optional<Employee> checkCredentials(String username, String password) {
        return em.createNamedQuery("Employee.checkCredentials",Employee.class).
                setParameter("username",username).setParameter("password",password).
                getResultStream().findFirst();
    }

    public List<Service> findAllServices() {
        return em.createNamedQuery("Service.findAllServices",Service.class).getResultList();
    }

    public List<Service> findServiceListByID(List<Integer> serviceIDList){
        return serviceIDList.stream().map(x -> em.find(Service.class, x)).collect(Collectors.toList());
    }
}
