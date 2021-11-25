package it.polimi.db2_project.ejb.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Alert implements Serializable {
    @Id
    private int ID;

    @Id
    @ManyToOne
    @JoinColumn(name = "username")
    private Customer customer;

    private Date datetime;
    private float amount;
    private String mail;
}
