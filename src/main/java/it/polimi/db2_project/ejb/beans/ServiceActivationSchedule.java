package it.polimi.db2_project.ejb.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @ManyToMany
    @JoinTable(name = "schedule_service_link", joinColumns = {@JoinColumn(name = "ID_user"),
            @JoinColumn(name = "ID_schedule")}, inverseJoinColumns = {@JoinColumn(name = "ID_service")})
    private List<Service> services;

    @ManyToMany
    @JoinTable(name = "opt_product_schedule_link", joinColumns = {@JoinColumn(name = "ID_user"),
            @JoinColumn(name = "ID_schedule")}, inverseJoinColumns = {@JoinColumn(name = "ID_opt_product")})
    private List<OptionalProduct> optionalProducts;

}
