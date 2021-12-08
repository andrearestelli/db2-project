package it.polimi.db2_project.ejb.services;

import it.polimi.db2_project.ejb.beans.Customer;
import it.polimi.db2_project.ejb.beans.Order;
import it.polimi.db2_project.ejb.beans.ServicePackage;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Stateless
public class OrderService {
    @PersistenceContext(name = "telcoEJB")
    private EntityManager em;

    public Integer createOrder(Date date_hour, int total_value, Date sub_date, Customer user_order, ServicePackage servicePackage) {
        Order order = new Order(date_hour,total_value,sub_date,user_order,servicePackage);
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

}
