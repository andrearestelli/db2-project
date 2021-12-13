package it.polimi.db2_project.web.utils;

import java.util.List;

public class RawServicePackage {
    private Integer ID;
    private String name;
    private Integer validityPeriod;
    private double monthly_fee;
    private List<Integer> services;
    private List<Integer> optionalProducts;

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Integer getValidityPeriod() {
        return validityPeriod;
    }

    public double getMonthly_fee() {
        return monthly_fee;
    }

    public List<Integer> getServices() {
        return services;
    }

    public List<Integer> getOptionalProducts() {
        return optionalProducts;
    }
}
