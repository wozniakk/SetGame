package com.example.kamil.model;

/**
 * Created by kamil on 13.03.15.
 */
public class Card {

    private Number number;
    private Symbol symbol;
    private Shading shading;
    private Color color;

    public Number getNumber() {
        return number;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Shading getShading() {
        return shading;
    }

    public Color getColor() {
        return color;
    }

    public Card(Number number, Symbol symbol, Shading shading, Color color) {

        this.number = number;
        this.symbol = symbol;
        this.shading = shading;
        this.color = color;

    }

    public String getResourceName() {

        return new StringBuilder(shading.getValue())
                .append(symbol.getValue())
                .append(color.getValue())
                .append(number.getValue())
                .toString();

    }

}
