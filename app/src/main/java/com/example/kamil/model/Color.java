package com.example.kamil.model;

/**
 * Created by kamil on 13.03.15.
 */
public enum Color {

    RED("red"), PURPLE("purple"), GREEN("green");

    private final String value;

    private Color(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
