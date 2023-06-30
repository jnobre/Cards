package com.atexo.cards.services;

import com.atexo.cards.models.PlayingCard;
import com.atexo.cards.models.enums.Suit;
import com.atexo.cards.models.enums.Value;
import com.atexo.cards.models.interfaces.Card;
import com.atexo.cards.services.interfaces.CardGame;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StandardCardGame implements CardGame {

    private final int HAND_SIZE = 10;
    private final List<Suit> suits;
    private final List<Value> values;

    public StandardCardGame() {
        suits = List.of(Suit.DIAMONDS, Suit.HEARTS, Suit.SPADES, Suit.CLUBS);
        values = List.of(Value.ACE, Value.FIVE, Value.TEN, Value.EIGHT,
                Value.SIX, Value.SEVEN, Value.FOUR, Value.TWO, Value.THREE,
                Value.NINE, Value.QUEEN, Value.KING, Value.JACK);
    }

    @Override
    public List<Card> generateHand() {
        List<Card> hand = new ArrayList<>();
        Set<Card> generatedCards = new HashSet<>();

        while (hand.size() < HAND_SIZE) {
            Suit randomSuit = getRandomElement(suits);
            Value randomValue = getRandomElement(values);
            Card card = new PlayingCard(randomSuit, randomValue);

            if (!generatedCards.contains(card)) {
                hand.add(card);
                generatedCards.add(card);
            }
        }

        return hand;
    }

    @Override
    public List<Suit> generateRandomSuitOrder() {
        return generateRandomOrder(suits);
    }

    @Override
    public List<Value> generateRandomValueOrder() {
        return generateRandomOrder(values);
    }

    @Override
    public List<Card> sortHand(List<Card> hand, List<Suit> suitOrder, List<Value> valueOrder) {
        if (suitOrder == null || valueOrder == null) {
            return hand; // Return the original hand if either suitOrder or valueOrder is null
        }

        if (suitOrder.size() != Suit.values().length || valueOrder.size() != Value.values().length) {
            throw new IllegalArgumentException("Invalid suit or value order list size");
        }
        List<Card> sortedHand = new ArrayList<>(hand);
        sortedHand.sort(Comparator.comparingInt(card ->
                suitOrder.indexOf(card.suit()) * valueOrder.size() + valueOrder.indexOf(card.value())));
        return sortedHand;
    }

    @Override
    public <T> List<T> generateRandomOrder(List<T> items) {
        List<T> randomOrder = new ArrayList<>(items);
        Collections.shuffle(randomOrder);
        return randomOrder;
    }

    @Override
    public <T> T getRandomElement(List<T> list) {
        return list.get((int) (Math.random() * list.size()));
    }

}
