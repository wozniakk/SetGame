package com.example.kamil.model;

/**
 * Created by kamil on 13.03.15.
 */
public enum Number {

    ONE(1), TWO(2), THREE(3);

    private final int value;

    private Number(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
