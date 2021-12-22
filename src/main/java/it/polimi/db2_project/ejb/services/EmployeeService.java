package it.polimi.db2_project.ejb.services;

import it.polimi.db2_project.ejb.beans.*;

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

    public List<Alert> findAllAlerts(){
        return em.createNamedQuery("Alert.findAllAlerts", Alert.class).getResultList();
    }

    public List<Order> findAllRejectedOrders(){
        return em.createNamedQuery("Order.findAllRejectedOrders", Order.class).getResultList();
    }

    public List<Customer> findInsolventCustomers(){
        return em.createNamedQuery("Customer.findInsolventCustomers", Customer.class).getResultList();
    }

    public List<Service> findServiceListByID(List<Integer> serviceIDList){
        return serviceIDList.stream().map(x -> em.find(Service.class, x)).collect(Collectors.toList());
    }
}
