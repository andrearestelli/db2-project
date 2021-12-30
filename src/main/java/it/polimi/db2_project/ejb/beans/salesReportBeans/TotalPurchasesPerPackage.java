package it.polimi.db2_project.ejb.beans.salesReportBeans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "total_purchases_per_package", schema = "telcodb")
@NamedQueries(
        {
                @NamedQuery(name = "TotalPurchasesPerPackage.retrieveAll"
                        ,query = "SELECT tp " +
                        "FROM TotalPurchasesPerPackage tp")
        }
)

public class TotalPurchasesPerPackage implements Serializable {
        @Id
        private String packageName;
        private Integer total_purchases;

        public TotalPurchasesPerPackage() {}

        public String getPackageName() {
                return packageName;
        }

        public Integer getTotal_purchases() {
                return total_purchases;
        }
}
