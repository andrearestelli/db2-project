package it.polimi.db2_project.web.utils;

import java.util.List;

public class RawServicePackage {
    private String servicePackageName;
    private Integer validityPeriod;
    private double monthlyFee;
    private List<Integer> serviceList;
    private List<Integer> optionalProductList;

    public String getName() {
        return servicePackageName;
    }

    public Integer getValidityPeriod() {
        return validityPeriod;
    }

    public double getMonthly_fee() {
        return monthlyFee;
    }

    public List<Integer> getServices() {
        return serviceList;
    }

    public List<Integer> getOptionalProducts() {
        return optionalProductList;
    }
}
