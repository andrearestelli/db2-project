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
    private ServiceType type;
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

    public enum ServiceType{
        FIXED_PHONE ("Fixed Phone"),
        MOBILE_PHONE ("Mobile Phone"),
        FIXED_INTERNET ("Fixed Internet"),
        MOBILE_INTERNET ("Mobile Internet");
        private String type;

        ServiceType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

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
