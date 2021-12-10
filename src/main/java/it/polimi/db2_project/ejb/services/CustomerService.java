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
        em.persist(customer);
        em.flush();//TODO comments
    }

    public Optional<Customer> findCustomerByUsername(String username) {
        return em.createNamedQuery("Customer.findByUsername", Customer.class)
                .setParameter("username", username)
                .getResultStream().findFirst();
    }

    public void setInsolventTrue(Customer customer) {
        customer.setInsolvent(true);
        em.merge(customer);//TODO commentare
    }

    public void setInsolventFalse(Customer customer) {
        customer.setInsolvent(false);
        em.merge(customer);//TODO commentare
    }


}
