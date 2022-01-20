package it.polimi.db2_project.ejb.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "service_activation_schedule", schema = "telcodb")
public class ServiceActivationSchedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Id
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private Customer customer;

    private Date activation_date;
    private Date deactivation_date;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    // referencedColumnName is used because it is a bridge table
    // that includes an entity with a key composed of two ids. In this case it is
    // therefore also necessary to specify which column of the ServiceActivationSchedule
    // table the columns of the bridge table refer to.
    @JoinTable(
            name = "schedule_service_link",
            joinColumns = {
                    @JoinColumn(name = "ID_user", referencedColumnName = "user_id"),
                    @JoinColumn(name = "ID_schedule", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ID_service")})
    private List<Service> services;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    // referencedColumnName is used because it is a bridge table
    // that includes an entity with a key composed of two ids. In this case it is
    // therefore also necessary to specify which column of the ServiceActivationSchedule
    // table the columns of the bridge table refer to.
    @JoinTable(
            name = "opt_product_schedule_link",
            joinColumns = {
                    @JoinColumn(name = "ID_user", referencedColumnName = "user_id"),
                    @JoinColumn(name = "ID_schedule", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ID_opt_product")})
    private List<OptionalProduct> optionalProducts;

    public ServiceActivationSchedule(Customer customer, Date activation_date, Date deactivation_date, List<Service> services, List<OptionalProduct> optionalProducts) {
        this.customer = customer;
        this.activation_date = activation_date;
        this.deactivation_date = deactivation_date;
        this.services = services;
        this.optionalProducts = optionalProducts;
    }

    public ServiceActivationSchedule() {

    }
}
