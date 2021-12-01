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

    /*
    @OneToMany(mappedBy = "id_package")
    private List<Order>
     */

    @ManyToMany
    @JoinTable(
            name = "package_opt_product_link",
            joinColumns = {
                    @JoinColumn(name = "id_package")},
            inverseJoinColumns = {@JoinColumn(name = "id_opt_product")})
    private List<OptionalProduct> optionalProducts;

    @ManyToMany
    @JoinTable(
            name = "service_to_package_link",
            joinColumns = {
                    @JoinColumn(name = "ID_package")},
            inverseJoinColumns = {@JoinColumn(name = "ID_service")})
    private List<Service> services;


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
}
