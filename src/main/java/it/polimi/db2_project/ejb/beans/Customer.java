package it.polimi.db2_project.ejb.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customer", schema = "telcodb")
@NamedQueries(
        {
                @NamedQuery(name = "Customer.checkCredentials"
                        ,query = "SELECT c " +
                        "FROM Customer c " +
                        "WHERE c.username = :username AND c.password = :password")
                ,
                @NamedQuery(name ="Customer.findByUsername"
                        ,query = "SELECT c " +
                        "FROM Customer c " +
                        "WHERE c.username = :username")
                ,
                @NamedQuery(name = "Customer.findInsolventCustomers"
                        ,query = "SELECT c " +
                        "FROM Customer c " +
                        "WHERE c.insolvent > 0")
        }
)
public class Customer implements Serializable {
    @Id
    private String username;
    private String password;
    private String mail;
    @Column (name = "insolvent")
    private Integer insolvent;

    public Customer(String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.insolvent = 0;
    }

/*  Can be omitted */
    @OneToMany(mappedBy = "userOrderer", orphanRemoval = true)
    private List<Order> orders;

    @OneToMany(mappedBy = "customer",orphanRemoval = true)
    private List<Alert> alerts;

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private List<ServiceActivationSchedule> serviceActivationScheduleList;

    public Customer() {

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public boolean isInsolvent() {
        return insolvent!=0;
    }

    public String getUsername() {
        return username;
    }
}
