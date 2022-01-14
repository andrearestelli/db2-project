package it.polimi.db2_project.ejb.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "service_package", schema = "telcodb")
@NamedQueries(
        {
                @NamedQuery(name = "ServicePackage.findAllPackages"
                        ,query = "Select sp " +
                        "FROM ServicePackage sp ")
        }
)

public class ServicePackage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String name;
    private int validity_period;
    private double monthly_fee;

    // Lazy fetch policy in order to improve performance when optional products are not explicitly needed
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "package_opt_product_link",
            joinColumns = {
                    @JoinColumn(name = "id_package")},
            inverseJoinColumns = {@JoinColumn(name = "id_opt_product")})
    private List<OptionalProduct> optionalProducts;

    // Lazy fetch policy in order to improve performance when services are not explicitly needed
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "service_to_package_link",
            joinColumns = {
                    @JoinColumn(name = "ID_package")},
            inverseJoinColumns = {@JoinColumn(name = "ID_service")})
    private List<Service> services;

    public ServicePackage(){

    }

    public ServicePackage(String name, int validity_period, double monthly_fee, List<OptionalProduct> optionalProducts, List<Service> services) {
        this.name = name;
        this.validity_period = validity_period;
        this.monthly_fee = monthly_fee;
        this.optionalProducts = optionalProducts;
        this.services = services;
    }

    public void setID(Integer id) {
        this.ID = id;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getValidity_period() {
        return validity_period;
    }

    public double getMonthly_fee() {
        return monthly_fee;
    }

    public List<OptionalProduct> getOptionalProducts() {
        return optionalProducts;
    }

    public List<Service> getServices() {
        return services;
    }
}
