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
    private List<OptionalProduct> optionalProducts;

    public void setID(Integer id) {
        this.ID = id;
    }

    public Integer getID() {
        return ID;
    }
}
