package it.polimi.db2_project.ejb.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order", schema = "telcodb")
@NamedQueries(
        {
                @NamedQuery(name = "Order.findRejectedOrdersByUser",
                        query = "SELECT o " +
                        "FROM Order o " +
                        "WHERE o.userOrderer = :username AND o.state = :statetype ")
                ,
                @NamedQuery(name = "Order.findAllRejectedOrders",
                        query = "SELECT o " +
                        "FROM Order o " +
                        "WHERE o.state = 'REJECTED'")
        }
)

public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private Date date_hour;
    private String state;
    private double total_value;
    private Date sub_date;

    public Order(Date date_hour, double total_value, Date sub_date, Customer userOrderer, ServicePackage id_package, List<OptionalProduct> optionalProducts) {
        this.date_hour = date_hour;
        this.total_value = total_value;
        this.state = StateType.PENDING.getText();
        this.sub_date = sub_date;
        this.userOrderer = userOrderer;
        this.id_package = id_package;
        this.optionalProducts = optionalProducts;
    }
    public enum StateType{
        VALID("VALID"),REJECTED("REJECTED"),PENDING("PENDING");
        private String text;
        StateType(String text) {
            this.text = text;
        }
        public String getText()
        {return this.text;}
    }

    @ManyToOne
    @JoinColumn(name = "user_orderer")
    private Customer userOrderer;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_package")
    private ServicePackage id_package;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(
            name = "order_opt_product_link",
            joinColumns = {
                    @JoinColumn(name = "IDorder")},
            inverseJoinColumns = {@JoinColumn(name = "ID_optional_product")})
    private List<OptionalProduct> optionalProducts;

    public Order() {

    }

    public void setID(Integer id) {
        this.ID = id;
    }

    public Integer getID() {
        return ID;
    }

    public Date getDate_hour() {
        return date_hour;
    }

    public String getState() {
        return state;
    }

    public double getTotal_value() {
        return total_value;
    }

    public Date getSub_date() {
        return sub_date;
    }

    public Customer getUserOrderer() {
        return userOrderer;
    }

    public ServicePackage getId_package() {
        return id_package;
    }

    public List<OptionalProduct> getOptionalProducts() {
        return optionalProducts;
    }

    public void setState(StateType state) {
        this.state = state.getText();
    }
}
