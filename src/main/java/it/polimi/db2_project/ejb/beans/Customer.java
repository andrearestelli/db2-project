package it.polimi.db2_project.ejb.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customer", schema = "telcodb")
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

    public void addInsolvent() {this.insolvent++;}

    public void decrementInsolvent(){this.insolvent--;}

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
