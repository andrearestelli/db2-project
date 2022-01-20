package it.polimi.db2_project.ejb.services;

import it.polimi.db2_project.ejb.beans.*;
import it.polimi.db2_project.web.utils.DateHandler;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
public class OrderService {
    @PersistenceContext(name = "telcoEJB")
    private EntityManager em;

    public Integer createOrder(Date date_hour, double total_value, Date sub_date, Customer user_order, ServicePackage servicePackage, List<OptionalProduct> optionalProductList) {
        Order order = new Order(date_hour,total_value,sub_date,user_order,servicePackage,optionalProductList);
        // Persist to move the new created instance to the managed state
        // Flush to ask the Persistence provider to write changes as soon as possible to the database
        em.persist(order);
        em.flush();
        return order.getID();
    }

    public Order findByID(Integer ID)
    {
        return em.find(Order.class,ID);
    }

    public void setStateByID(Integer ID, Order.StateType stateType) {
        //It's not necessary to use the merge method to make the Order object managed and apply the updates on
        //the database, because the second way of making an object managed is to extract the object from the
        //database by the find method
        Order order = em.find(Order.class,ID);
        order.setState(stateType);
    }

    public void createActivationSchedule(Customer customer, Order order) {
        ServiceActivationSchedule serviceActivationSchedule = new ServiceActivationSchedule(customer,
                order.getSub_date(), DateHandler.computeEndingDate(order.getSub_date(),
                order.getId_package().getValidity_period()),
                order.getId_package().getServices(),order.getOptionalProducts());
        // Persist to move the new created instance to the managed state
        // Flush to ask the Persistence provider to write changes as soon as possible to the database
        em.persist(serviceActivationSchedule);
        em.flush();
    }

    public List<Order> findRejectedOrdersByUser(Customer username) {
        return em.createNamedQuery("Order.findRejectedOrdersByUser", Order.class).
                setParameter("username",username).setParameter("statetype", Order.StateType.REJECTED.getText()).getResultList();
    }
}
