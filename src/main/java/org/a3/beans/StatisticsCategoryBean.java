package org.a3.beans;

import java.util.List;

public class StatisticsCategoryBean {
    private List<StatisticsCategoryBean> subCategoryBeans;

    private String categoryName;

    private int totalUnresolved;
    private int resolvedLastSevenDays;

    public List<StatisticsCategoryBean> getSubCategoryBeans() {
        return subCategoryBeans;
    }

    public void setSubCategoryBeans(List<StatisticsCategoryBean> subCategoryBeans) {
        this.subCategoryBeans = subCategoryBeans;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getTotalUnresolved() {
        return totalUnresolved;
    }

    public void setTotalUnresolved(int totalUnresolved) {
        this.totalUnresolved = totalUnresolved;
    }

    public int getResolvedLastSevenDays() {
        return resolvedLastSevenDays;
    }

    public void setResolvedLastSevenDays(int unresolvedSevenDays) {
        this.resolvedLastSevenDays = unresolvedSevenDays;
    }
}
