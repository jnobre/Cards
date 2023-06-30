package com.atexo.cards.models;

import com.atexo.cards.models.enums.Suit;
import com.atexo.cards.models.enums.Value;
import com.atexo.cards.models.interfaces.Card;

import java.util.List;

public record GameResponse(List<Card> hand, List<Suit> suiteOrder, List<Value> valueOrder, List<Card> sortedHand) {
}