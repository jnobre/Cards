package com.atexo.cards.models;

import com.atexo.cards.models.enums.Suit;
import com.atexo.cards.models.enums.Value;
import com.atexo.cards.models.interfaces.Card;

public record PlayingCard(Suit suit, Value value) implements Card {

    @Override
    public String toString() {
        return suit.getName() + " " + value.getName();
    }
}
