package it.polimi.db2_project.ejb.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "service", schema = "telcodb")
@NamedQueries(
        {
                @NamedQuery(name = "Service.findAllServices"
                        ,query = "Select s " +
                        "FROM Service s ")
        }
)
public class Service implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer ID;
    private String type;
    private int minutes;
    private int sms;
    private float fee_minutes;
    private float fee_sms;
    private int gigabytes;
    private float fee_gigabytes;

    @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY)
    private List<ServiceActivationSchedule> serviceActivationScheduleList;

    @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY)
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

    public int getMinutes() {
        return minutes;
    }

    public int getSms() {
        return sms;
    }

    public float getFee_minutes() {
        return fee_minutes;
    }

    public float getFee_sms() {
        return fee_sms;
    }

    public int getGigabytes() {
        return gigabytes;
    }

    public float getFee_gigabytes() {
        return fee_gigabytes;
    }
}
