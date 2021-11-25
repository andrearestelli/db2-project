package it.polimi.db2_project.ejb.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class OptionalProduct implements Serializable {
    @Id
    private String name;
    private int validity_period;
    private double monthly_fee;

    /* TODO
    @ManyToMany(mappedBy = "optionalProducts")
    private List<ServicePackage> servicePackagesOwner;
    */

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
