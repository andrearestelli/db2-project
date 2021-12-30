package it.polimi.db2_project.ejb.beans.salesReportBeans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "total_value_of_sales", schema = "telcodb")
@NamedQueries(
        {
                @NamedQuery(name = "TotalValueOfSales.retrieveAll"
                        ,query = "SELECT tp " +
                        "FROM TotalValueOfSales tp")
        }
)
public class TotalValueOfSales implements Serializable {
    @Id
    private String packageName;
    private Double total_value_with_optp;
    private Double total_value_without_optp;

    public TotalValueOfSales() {
    }

    public String getPackageName() {
        return packageName;
    }

    public Double getTotal_value_with_optp() {
        return total_value_with_optp;
    }

    public Double getTotal_value_without_optp() {
        return total_value_without_optp;
    }
}
