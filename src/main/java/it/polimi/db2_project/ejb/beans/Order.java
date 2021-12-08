package it.polimi.db2_project.ejb.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "order", schema = "telcodb")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private Date date_hour;
    private String state; // TODO fare enum
    private int total_value;
    private Date sub_date;

    public Order(Date date_hour, int total_value, Date sub_date, Customer userOrderer, ServicePackage id_package) {
        this.date_hour = date_hour;
        this.total_value = total_value;
        this.state = StateType.PENDING.getText();
        this.sub_date = sub_date;
        this.userOrderer = userOrderer;
        this.id_package = id_package;
    }
    public enum StateType{
        VALID("VALID"),REJECTED("REJECTED"),PENDING("PENDING");
        private String text;
        StateType(String text) {
            this.text = text;
        }
        public String getText()
        {return this.text;}
    };

    @ManyToOne
    @JoinColumn(name = "user_orderer")
    private Customer userOrderer;

    @ManyToOne
    @JoinColumn(name = "id_package")
    private ServicePackage id_package;

    public Order() {

    }

    public void setID(Integer id) {
        this.ID = id;
    }

    public Integer getID() {
        return ID;
    }

    public void setState(StateType state) {
        this.state = state.getText();
    }
}
