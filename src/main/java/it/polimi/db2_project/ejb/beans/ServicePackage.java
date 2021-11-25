package it.polimi.db2_project.ejb.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class ServicePackage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    private String name;
    private int validity_period;
    private double monthly_fee;

    /* TODO
    @OneToMany(mappedBy = "id_package")
    private List<Order>
    */

    @ManyToMany
    @JoinTable(name = "package_opt_product_link", joinColumns = {@JoinColumn(name = "id_package")},
            inverseJoinColumns = {@JoinColumn(name = "id_opt_product")})
    private List<OptionalProduct> optionalProducts;

    @ManyToMany
    @JoinTable(name = "service_to_package_link", joinColumns = {@JoinColumn(name = "ID_package")},
            inverseJoinColumns = {@JoinColumn(name = "ID_service")})
    private List<Service> services;

    public void setID(Integer id) {
        this.ID = id;
    }

    public Integer getID() {
        return ID;
    }
}
