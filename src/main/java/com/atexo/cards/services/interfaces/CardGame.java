package com.atexo.cards.services.interfaces;


import com.atexo.cards.models.enums.Suit;
import com.atexo.cards.models.enums.Value;
import com.atexo.cards.models.interfaces.Card;


import java.util.List;

public interface CardGame {
    List<Card> generateHand();
    List<Suit> generateRandomSuitOrder();
    List<Value> generateRandomValueOrder();
    List<Card> sortHand(List<Card> hand, List<Suit> suitOrder, List<Value> valueOrder);
    <T> List<T> generateRandomOrder(List<T> items);
    <T> T getRandomElement(List<T> list);
}
