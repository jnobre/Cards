package com.atexo.cards.models.interfaces;


import com.atexo.cards.models.enums.Suit;
import com.atexo.cards.models.enums.Value;

public interface Card {
    Suit suit();
    Value value();
}
