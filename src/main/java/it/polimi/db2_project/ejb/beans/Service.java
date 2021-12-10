package it.polimi.db2_project.ejb.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "service", schema = "telcodb")
public class Service implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer ID;
    private String type; // TODO fare enum
    private int minutes;
    private int sms;
    private float fee_minutes;
    private float fee_sms;
    private int gigabytes;
    private float fee_gigabytes;

    // TODO verificare necessità
    @ManyToMany(mappedBy = "services")
    private List<ServiceActivationSchedule> serviceActivationScheduleList;

    //TODO verificare necessità
    @ManyToMany(mappedBy = "services")
    private List<ServicePackage> servicePackageList;

    public void setID(Integer id) {
        this.ID = id;
    }

    public String getType() {
        return type;
    }

    public Integer getID() {
        return ID;
    }
}
