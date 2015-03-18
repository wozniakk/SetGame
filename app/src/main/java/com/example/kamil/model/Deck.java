package com.example.kamil.model;

import android.util.Log;

/**
 * Created by kamil on 13.03.15.
 */
public class Deck {

    private final int DECK_SIZE = Number.values().length * Symbol.values().length * Shading.values().length * Color.values().length;

    public int getDECK_SIZE() {
        return DECK_SIZE;
    }

    private static Deck deck = new Deck();

    private Card[] cards = new Card[DECK_SIZE];

    public static Deck getInstance() {
        return deck;
    }

    private Deck() {

        int currentIndex = 0;
        for (Number number: Number.values())
            for (Symbol symbol: Symbol.values())
                for (Shading shading: Shading.values())
                    for (Color color: Color.values())
                        cards[currentIndex++] = new Card(number, symbol, shading, color);

    }

    public Card getCard(int index) {
        return cards[index];
    }

    public boolean isSet(Card[] cards) {

        if (cards[0].getNumber().equals(cards[1].getNumber())
                && cards[0].getNumber().equals(cards[2].getNumber())) {}
        else if (!cards[0].getNumber().equals(cards[1].getNumber())
                && !cards[0].getNumber().equals(cards[2].getNumber())
                && !cards[1].getNumber().equals(cards[2].getNumber())) {}
        else return false;
        if (cards[0].getSymbol().equals(cards[1].getSymbol())
                && cards[0].getSymbol().equals(cards[2].getSymbol())) {}
        else if (!cards[0].getSymbol().equals(cards[1].getSymbol())
                && !cards[0].getSymbol().equals(cards[2].getSymbol())
                && !cards[1].getSymbol().equals(cards[2].getSymbol())) {}
        else return false;
        if (cards[0].getShading().equals(cards[1].getShading())
                && cards[0].getShading().equals(cards[2].getShading())) {}
        else if (!cards[0].getShading().equals(cards[1].getShading())
                && !cards[0].getShading().equals(cards[2].getShading())
                && !cards[1].getShading().equals(cards[2].getShading())) {}
        else return false;
        if (cards[0].getColor().equals(cards[1].getColor())
                && cards[0].getColor().equals(cards[2].getColor())) {}
        else if (!cards[0].getColor().equals(cards[1].getColor())
                && !cards[0].getColor().equals(cards[2].getColor())
                && !cards[1].getColor().equals(cards[2].getColor())) {}
        else return false;
        return true;

    }

}
