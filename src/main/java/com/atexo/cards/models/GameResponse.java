package com.atexo.cards.models;

import com.atexo.cards.models.interfaces.Card;

import java.util.List;

public record GameResponse(List<Card> hand, List<Card> sortedHand) {
}