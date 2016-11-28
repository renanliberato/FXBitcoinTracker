package com.renanliberato.priceindex.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by renan on 11/22/2016.
 */
public class PriceIndex {
    private SimpleStringProperty date;

    private SimpleDoubleProperty index;

    public PriceIndex(String date, Double index) {
        this.date = new SimpleStringProperty(date);
        this.index = new SimpleDoubleProperty(index);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public double getIndex() {
        return index.get();
    }

    public SimpleDoubleProperty indexProperty() {
        return index;
    }

    public void setIndex(double index) {
        this.index.set(index);
    }
}
