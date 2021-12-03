package it.polimi.db2_project.web.utils;

import it.polimi.db2_project.ejb.beans.OptionalProduct;

import javax.swing.text.html.Option;
import java.util.List;

public class OptionalProductList {
    List<OptionalProduct> optionalProductList;

    public List<OptionalProduct> getOptionalProductList() {
        return optionalProductList;
    }

    public void setOptionalProductList(List<OptionalProduct> optionalProductList) {
        this.optionalProductList = optionalProductList;
    }
}
