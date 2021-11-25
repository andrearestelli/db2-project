package it.polimi.db2_project.ejb.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class ServiceActivationSchedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Id
    @ManyToOne
    @JoinColumn(name = "username")
    private Customer customer;

    private Date activation_date;
    private Date deactivation_date;

}
