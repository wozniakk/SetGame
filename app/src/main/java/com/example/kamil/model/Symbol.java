package com.example.kamil.model;

/**
 * Created by kamil on 13.03.15.
 */
public enum Symbol {

    SQUIGGLE("squiggle"), DIAMOND("diamond"), OVAL("oval");

    private final String value;

    private Symbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
