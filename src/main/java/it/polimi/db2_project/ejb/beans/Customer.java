package it.polimi.db2_project.ejb.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customer", schema = "telcodb")
public class Customer implements Serializable {
    @Id
    private String username;
    private String password;
    private String email;
    private boolean insolvent;

    @OneToMany(mappedBy = "userOrderer")
    private List<Order> orders;

    @OneToMany(mappedBy = "customer")
    private List<Alert> alerts;

    @OneToMany(mappedBy = "customer")
    private List<ServiceActivationSchedule> serviceActivationScheduleList;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
