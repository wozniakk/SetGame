package com.example.kamil.model;

/**
 * Created by kamil on 13.03.15.
 */
public enum Shading {

    SOLID("solid"), STRIPED("striped"), OPEN("open");

    private final String value;

    private Shading(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
