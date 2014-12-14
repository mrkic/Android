package com.example.mariyan.expenselist;

/**
 * Created by Mariyan on 19.11.2014 г..
 */
public class Bills {
    private String label;
    private float price;

    public Bills(String label, float price) {
        this.price = price;
        this.label = label;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
