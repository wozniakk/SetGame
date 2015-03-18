package com.example.kamil.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by kamil on 15.03.15.
 */
public class Game {

    private final int QUANTITY_OF_CARDS = 12;
    private final int SET_SIZE = 3;

    private int counterBad;
    private int counterGood;

    private ArrayList<Card> cardSet;

    public ArrayList<Card> getCardSet() {
        return cardSet;
    }

    private HashMap<HashSet<Card>, Boolean> sets;
    private Deck deck = Deck.getInstance();

    public Game() {

        cardSet = newSetOfCards(QUANTITY_OF_CARDS);
        sets = findAvailableSets(cardSet);
        counterGood = counterBad = 0;

    }

    private ArrayList<Card> newSetOfCards(int quantityOfCards) {

        ArrayList<Card> cardSet = new ArrayList<>(quantityOfCards);
        Random random = new Random();
        int cardIndex;
        for (int i = 0; i<quantityOfCards; ++i) {
            do {
                cardIndex = random.nextInt(deck.getDECK_SIZE());
            } while(cardSet.contains(deck.getCard(cardIndex)));
            cardSet.add(deck.getCard(cardIndex));
        }
        return cardSet;

    }

    private HashMap<HashSet<Card>, Boolean> findAvailableSets(ArrayList<Card> cardSet) {

        HashMap<HashSet<Card>, Boolean> sets = new HashMap<>();
        Card[] set = new Card[SET_SIZE];
        HashSet<Card> temp;
        for (int i = 0; i<=cardSet.size() - SET_SIZE; ++i) {
            for (int j = i + 1; j<cardSet.size()-1; ++j) {
                for (int k = j + 1; k<cardSet.size(); ++k) {
                    set[0] = cardSet.get(i);
                    set[1] = cardSet.get(j);
                    set[2] = cardSet.get(k);
                    if (deck.isSet(set)) {
                        temp = new HashSet<>();
                        for (Card card: set) temp.add(card);
                        sets.put(temp, Boolean.FALSE);
                    }
                }
            }
        }
        return sets;

    }

    public int getLeft() {
        int left = 0;
        for (Boolean value: sets.values())
            if (value.booleanValue() == false) left++;
        return left;
    }

    public int getBad() {
        return counterBad;
    }

    public void incrementBad() {
        counterBad++;
    }

    public int getGood() {
        return counterGood;
    }

    public void incrementGood() {
        counterGood++;
    }

    public Result checkSelected(int[] cardIndexes) {

        HashSet<Card> selectedCards = new HashSet<>();
        for (int i = 0; i<cardIndexes.length; ++i)
            selectedCards.add(cardSet.get(cardIndexes[i]));
        if (sets.containsKey(selectedCards) && sets.get(selectedCards).booleanValue() == false) {
            sets.remove(selectedCards);
            sets.put(selectedCards, Boolean.TRUE);
            incrementGood();
            return Result.GOOD;
        } else if (sets.containsKey(selectedCards)) {
            return Result.SELECTED;
        } else {
            incrementBad();
            return Result.BAD;
        }

    }

}
