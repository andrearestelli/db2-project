package it.polimi.db2_project.ejb.beans.salesreportbeans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "best_seller_optional_product", schema = "telcodb")
@NamedQueries(
        {
                @NamedQuery(name = "BestSellerOptionalProduct.retrieveBestSeller"
                        ,query = "SELECT bs " +
                        "FROM BestSellerOptionalProduct bs " +
                        "ORDER BY bs.total_value DESC ")
        }
)
public class BestSellerOptionalProduct implements Serializable {
    @Id
    private String optionalProduct;
    private Double total_value;

    public BestSellerOptionalProduct() {
    }

    public String getOptionalProduct() {
        return optionalProduct;
    }

    public Double getTotal_value() {
        return total_value;
    }
}
