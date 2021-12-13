package it.polimi.db2_project.ejb.services;

import it.polimi.db2_project.ejb.beans.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Stateless
public class CustomerService {

    @PersistenceContext(name = "telcoEJB")
    private EntityManager em;

    public Optional<Customer> checkCredentials(String username, String password) {
        return em.createNamedQuery("Customer.checkCredentials",Customer.class).
                setParameter("username",username).setParameter("password",password).
                getResultStream().findFirst();
    }

    public void registerCustomer(String username, String password, String mail) {
        Customer customer = new Customer(username,password,mail);
        // Persist to move the new created instance to the managed state
        // Flush to ask the Persistence provider to write changes as soon as possible to the database
        em.persist(customer);
        em.flush();
    }

    public Optional<Customer> findCustomerByUsername(String username) {
        return em.createNamedQuery("Customer.findByUsername", Customer.class)
                .setParameter("username", username)
                .getResultStream().findFirst();
    }

    public void addInsolvent(Customer customer) {
        customer.addInsolvent();
        // Merge used to change the state of customer to managed from detached
        em.merge(customer);
    }

    /*
    //TODO Valutare se rimuovere
    public void decrementInsolvent(Customer customer) {
        customer.decrementInsolvent();
        // Merge used to change the state of customer to managed from detached
        em.merge(customer);
    }*/


}
