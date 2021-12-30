package it.polimi.db2_project.ejb.beans.salesReportBeans;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "total_purchases_validity_period_per_package", schema = "telcodb")
@NamedQueries(
        {
                @NamedQuery(name = "TotalPurchasesValidityPeriodPerPackage.retrieveAll"
                        ,query = "SELECT tp " +
                        "FROM TotalPurchasesValidityPeriodPerPackage tp")
        }
)
public class TotalPurchasesValidityPeriodPerPackage implements Serializable {
    @Id
    private String packageName;
    @Id
    private Integer validity_period;
    private Integer total_purchases;

    public TotalPurchasesValidityPeriodPerPackage() {}

    public String getPackageName() {
        return packageName;
    }

    public Integer getTotal_purchases() {
        return total_purchases;
    }

    public Integer getValidity_period() {
        return validity_period;
    }
}
