package it.polimi.db2_project.ejb.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "order", schema = "telcodb")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    private Date date_hour;
    private String state; // TODO fare enum
    private int total_value;
    private Date sub_date;

    @ManyToOne
    @JoinColumn(name = "user_orderer")
    private Customer userOrderer;

    @ManyToOne
    @JoinColumn(name = "id_package")
    private ServicePackage id_package;

    public void setID(Integer id) {
        this.ID = id;
    }

    public Integer getID() {
        return ID;
    }
}
