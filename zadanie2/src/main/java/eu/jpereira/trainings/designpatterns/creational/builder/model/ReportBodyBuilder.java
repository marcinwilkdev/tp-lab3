package eu.jpereira.trainings.designpatterns.creational.builder.model;

import java.util.List;

public abstract class ReportBodyBuilder {
    protected Customer customer;
    protected List<SoldItem> soldItems;

    public ReportBodyBuilder customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public ReportBodyBuilder soldItems(List<SoldItem> soldItems) {
        this.soldItems = soldItems;
        return this;
    }

    public abstract ReportBody build();
}
