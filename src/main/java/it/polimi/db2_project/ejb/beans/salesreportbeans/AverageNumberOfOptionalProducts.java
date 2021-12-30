package it.polimi.db2_project.ejb.beans.salesreportbeans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "average_number_of_optional_products", schema = "telcodb")
@NamedQueries(
        {
                @NamedQuery(name = "AverageNumberOfOptionalProducts.retrieveAll"
                        ,query = "SELECT av " +
                        "FROM AverageNumberOfOptionalProducts av")
        }
)
public class AverageNumberOfOptionalProducts implements Serializable {
    @Id
    private String packageName;
    private Integer number_of_sales_package;
    private Integer number_of_opt_products;
    private Double average_of_opt_products;

    public AverageNumberOfOptionalProducts() {
    }

    public String getPackageName() {
        return packageName;
    }

    public Integer getNumber_of_sales_package() {
        return number_of_sales_package;
    }

    public Integer getNumber_of_opt_products() {
        return number_of_opt_products;
    }

    public Double getAverage_of_opt_products() {
        return average_of_opt_products;
    }
}
