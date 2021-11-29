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
    private String name;
    private int validity_period;
    private double monthly_fee;

    /* TODO
    @ManyToMany(mappedBy = "optionalProducts")
    private List<ServicePackage> servicePackagesOwner;


    // TODO verificare necessità
    @ManyToMany(mappedBy = "optionalProducts")
    private List<ServiceActivationSchedule> serviceActivationScheduleList;
*/
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
