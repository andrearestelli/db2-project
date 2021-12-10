package it.polimi.db2_project.ejb.services;

import it.polimi.db2_project.ejb.beans.OptionalProduct;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class OptionalProductService {
    @PersistenceContext(name = "telcoEJB")
    private EntityManager em;

    public List<OptionalProduct> findOptionalProductListByID(List<Integer> optionalProductList) {
        List<OptionalProduct> optionalProducts = new ArrayList<>();
        for(Integer op : optionalProductList)
        {
            optionalProducts.add(em.find(OptionalProduct.class,op));
        }
        return optionalProducts;
    }

}
