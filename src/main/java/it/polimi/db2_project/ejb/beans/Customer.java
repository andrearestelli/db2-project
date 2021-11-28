package it.polimi.db2_project.ejb.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries(
        {
        @NamedQuery(name = "Customer.checkCredentials"
                        ,query = "Select c " +
                        "FROM Customer c " +
                        "WHERE c.username = :username AND c.password = :password")
        ,
        @NamedQuery(name ="Customer.findByUsername"
                    ,query = "Select c " +
                            "FROM Customer c " +
                            "WHERE c.username = :username")
        }
)

@Table(name = "customer", schema = "telcodb")
public class Customer implements Serializable {
    @Id
    private String username;
    private String password;
    private String email;
    private boolean insolvent;

    public Customer(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.insolvent = false;
    }

    @OneToMany(mappedBy = "userOrderer")
    private List<Order> orders;

    @OneToMany(mappedBy = "customer")
    private List<Alert> alerts;

    @OneToMany(mappedBy = "customer")
    private List<ServiceActivationSchedule> serviceActivationScheduleList;

    public Customer() {

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setInsolvent(boolean insolvent) {this.insolvent = insolvent;}

    public String getUsername() {
        return username;
    }
}
