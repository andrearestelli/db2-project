package it.polimi.db2_project.ejb.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "optional_product", schema = "telcodb")
public class OptionalProduct implements Serializable {
    @Id
    private Integer ID;
    private String name;
    private int validity_period;
    private double monthly_fee;

    /* TODO
    @ManyToMany(mappedBy = "optionalProducts")
    private List<ServicePackage> servicePackagesOwner;

    @ManyToMany(mappedBy = "optionalProducts")
    private List<Order> orders;

    // TODO verificare necessità
    @ManyToMany(mappedBy = "optionalProducts")
    private List<ServiceActivationSchedule> serviceActivationScheduleList;
*/

    public Integer getID() {
        return ID;
    }

    public int getValidity_period() {
        return validity_period;
    }

    public double getMonthly_fee() {
        return monthly_fee;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
