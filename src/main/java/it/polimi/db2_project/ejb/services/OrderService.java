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
        em.persist(order);
        em.flush();//TODO comments
        return order.getID();
    }

    public Order findByID(Integer ID)
    {
        return em.find(Order.class,ID);
    }

    public void setStateByID(Integer ID, Order.StateType stateType)
    {
        Order order = em.find(Order.class,ID);
        order.setState(stateType);
    }

    public void createActivationSchedule(Customer customer, Order order)
    {
        ServiceActivationSchedule serviceActivationSchedule = new ServiceActivationSchedule(customer,
                order.getSub_date(), DateHandler.computeEndingDate(order.getSub_date(),
                order.getId_package().getValidity_period()),
                order.getId_package().getServices(),order.getOptionalProducts());
        //TODO Mandare la lista di optional product selezionati
        em.persist(serviceActivationSchedule);
        em.flush();
    }

    public List<Order> findRejectedOrdersByUser(Customer username) {
        return em.createNamedQuery("Order.findRejectedOrdersByUser", Order.class).
                setParameter("username",username).setParameter("statetype", Order.StateType.REJECTED.getText()).getResultList();
    }
}
