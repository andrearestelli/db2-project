package it.polimi.db2_project.ejb.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@NamedQuery(name = "Employee.checkCredentials"
        ,query = "Select e " +
        "FROM Employee e " +
        "WHERE e.username = :username AND e.password = :password")
@Table(name = "employee", schema = "telcodb")
public class Employee implements Serializable {
    @Id
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
