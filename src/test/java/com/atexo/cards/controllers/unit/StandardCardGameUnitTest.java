package com.atexo.cards.controllers.unit;

import com.atexo.cards.models.enums.Suit;
import com.atexo.cards.models.enums.Value;
import com.atexo.cards.models.interfaces.Card;
import com.atexo.cards.services.StandardCardGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class StandardCardGameUnitTest {

    private StandardCardGame cardGame;

    @BeforeEach
    public void setUp() {
        cardGame = new StandardCardGame();
    }

    @Test
    public void testGenerateHand() {
        List<Card> hand = cardGame.generateHand();

        // Assert that the generated hand has 10 cards
        Assertions.assertEquals(10, hand.size());

        // Assert that all cards in the hand have valid suits and values
        for (Card card : hand) {
            Suit suit = card.suit();
            Value value = card.value();

            // Assert that the suit is one of the valid suits
            Assertions.assertTrue(
                    Arrays.asList(Suit.values()).contains(suit),
                    "Invalid suit: " + suit.getName()
            );

            // Assert that the value is one of the valid values
            Assertions.assertTrue(
                    Arrays.asList(Value.values()).contains(value),
                    "Invalid value: " + value.getName()
            );
        }


        // Assert that there are no duplicates in the hand
        Set<Card> uniqueCards = new HashSet<>(hand);
        Assertions.assertEquals(hand.size(), uniqueCards.size(), "The hand should not have duplicates");

    }

    @Test
    public void testGenerateRandomValueOrder() {
        List<Value> valueOrder = cardGame.generateRandomValueOrder();

        // Assert that the generated value order has all values
        Assertions.assertEquals(13, valueOrder.size());
        Assertions.assertTrue(valueOrder.containsAll(Arrays.asList(Value.ACE, Value.FIVE, Value.TEN, Value.EIGHT,
                Value.SIX, Value.SEVEN, Value.FOUR, Value.TWO, Value.THREE,
                Value.NINE, Value.QUEEN, Value.KING, Value.JACK)));

        // Assert that there are no duplicates in the value order
        Set<Value> uniqueValues = new HashSet<>(valueOrder);
        Assertions.assertEquals(valueOrder.size(), uniqueValues.size(), "The value order should not have duplicates");

    }

    @Test
    public void testGenerateRandomSuitOrder() {
        List<Suit> suitOrder = cardGame.generateRandomSuitOrder();

        // Assert that the generated suit order has all suits
        Assertions.assertEquals(4, suitOrder.size());
        Assertions.assertTrue(suitOrder.containsAll(Arrays.asList(Suit.DIAMONDS, Suit.HEARTS, Suit.SPADES, Suit.CLUBS)));

        // Assert that there are no duplicates in the suit order
        Set<Suit> uniqueSuits = new HashSet<>(suitOrder);
        Assertions.assertEquals(suitOrder.size(), uniqueSuits.size(), "The suit order should not have duplicates");

    }


    @Test
    public void testSortHandWithCustomOrder() {
        StandardCardGame cardGame = new StandardCardGame();

        // Define the custom suit and value order
        List<Suit> suitOrder = Arrays.asList(Suit.HEARTS, Suit.DIAMONDS, Suit.CLUBS, Suit.SPADES);
        List<Value> valueOrder = Arrays.asList(Value.NINE, Value.KING, Value.FOUR, Value.THREE, Value.EIGHT,
                Value.TEN, Value.JACK, Value.ACE, Value.FIVE, Value.SEVEN, Value.QUEEN, Value.SIX, Value.TWO);

        // Generate a hand
        List<Card> hand = cardGame.generateHand();

        // Sort the hand based on the custom order
        List<Card> sortedHand = cardGame.sortHand(hand, suitOrder, valueOrder);

        // Verify the ordering of suits
        for (int i = 0; i < sortedHand.size() - 1; i++) {
            Suit currentSuit = sortedHand.get(i).suit();
            Suit nextSuit = sortedHand.get(i + 1).suit();
            int currentSuitIndex = suitOrder.indexOf(currentSuit);
            int nextSuitIndex = suitOrder.indexOf(nextSuit);
            Assertions.assertTrue(currentSuitIndex <= nextSuitIndex,
                    "Invalid suit ordering: " + currentSuit + " should come before " + nextSuit);
        }

        // Verify the ordering of values within each suit
        Suit currentSuit = sortedHand.get(0).suit();
        int currentSuitIndex = suitOrder.indexOf(currentSuit);
        int currentValueIndex = valueOrder.indexOf(sortedHand.get(0).value());

        for (int i = 1; i < sortedHand.size(); i++) {
            Card card = sortedHand.get(i);
            Suit suit = card.suit();
            Value value = card.value();
            int suitIndex = suitOrder.indexOf(suit);
            int valueIndex = valueOrder.indexOf(value);

            if (suit.equals(currentSuit)) {
                Assertions.assertTrue(valueIndex >= currentValueIndex,
                        "Invalid value ordering within suit " + suit + ": " + value + " should come after " +
                                sortedHand.get(i - 1).value());
            } else {
                Assertions.assertTrue(suitIndex >= currentSuitIndex,
                        "Invalid suit ordering: " + suit + " should come after " + currentSuit);
                currentSuit = suit;
                currentSuitIndex = suitIndex;
                currentValueIndex = valueIndex;
            }
        }
    }

    @Test
    public void testSortHandWithInvalidOrder() {
        StandardCardGame cardGame = new StandardCardGame();

        // Define the custom suit and value order
        List<Suit> suitOrder = Arrays.asList(Suit.HEARTS, Suit.DIAMONDS, Suit.CLUBS, Suit.SPADES);
        List<Value> valueOrder = Arrays.asList(Value.NINE, Value.KING, Value.FOUR, Value.THREE, Value.EIGHT,
                Value.TEN, Value.JACK, Value.ACE, Value.FIVE, Value.SEVEN, Value.QUEEN, Value.SIX, Value.TWO);

        // Generate a hand
        List<Card> hand = cardGame.generateHand();

        // Shuffle the hand to make it different from the expected order
        Collections.shuffle(hand);

        // Sort the hand based on the custom order
        List<Card> sortedHand = cardGame.sortHand(hand, suitOrder, valueOrder);

        // Verify that the sorted hand is not equal to the original hand
        Assertions.assertNotEquals(hand, sortedHand, "Hand should be sorted but is not.");

        // Verify that the sorting algorithm did not change the order of suits
        for (int i = 0; i < sortedHand.size() - 1; i++) {
            Suit currentSuit = sortedHand.get(i).suit();
            Suit nextSuit = sortedHand.get(i + 1).suit();
            int currentSuitIndex = suitOrder.indexOf(currentSuit);
            int nextSuitIndex = suitOrder.indexOf(nextSuit);
            Assertions.assertTrue(currentSuitIndex <= nextSuitIndex,
                    "Invalid suit ordering: " + currentSuit + " should come before " + nextSuit);
        }

        // Verify that the sorting algorithm did not change the order of values within each suit
        Suit currentSuit = sortedHand.get(0).suit();
        int currentSuitIndex = suitOrder.indexOf(currentSuit);
        int currentValueIndex = valueOrder.indexOf(sortedHand.get(0).value());

        for (int i = 1; i < sortedHand.size(); i++) {
            Card card = sortedHand.get(i);
            Suit suit = card.suit();
            Value value = card.value();
            int suitIndex = suitOrder.indexOf(suit);
            int valueIndex = valueOrder.indexOf(value);

            if (suit.equals(currentSuit)) {
                Assertions.assertTrue(valueIndex >= currentValueIndex,
                        "Invalid value ordering within suit " + suit + ": " + value + " should come after " +
                                sortedHand.get(i - 1).value());
            } else {
                Assertions.assertTrue(suitIndex >= currentSuitIndex,
                        "Invalid suit ordering: " + suit + " should come after " + currentSuit);
                currentSuit = suit;
                currentSuitIndex = suitIndex;
                currentValueIndex = valueIndex;
            }
        }
    }

    @Test
    public void testSortHandWithEmptyHand() {
        StandardCardGame cardGame = new StandardCardGame();
        List<Card> emptyHand = new ArrayList<>();
        List<Suit> suitOrder = Arrays.asList(Suit.HEARTS, Suit.DIAMONDS, Suit.CLUBS, Suit.SPADES);
        List<Value> valueOrder = Arrays.asList(Value.ACE, Value.TWO, Value.THREE, Value.FOUR, Value.FIVE, Value.SIX,
                Value.SEVEN, Value.EIGHT, Value.NINE, Value.TEN, Value.JACK, Value.QUEEN, Value.KING);

        List<Card> sortedEmptyHand = cardGame.sortHand(emptyHand, suitOrder, valueOrder);

        Assertions.assertEquals(emptyHand, sortedEmptyHand, "Empty hand should remain unchanged");
    }

    @Test
    public void testSortHandWithNullSuitOrder() {
        StandardCardGame cardGame = new StandardCardGame();
        List<Card> hand = cardGame.generateHand();
        List<Value> valueOrder = Arrays.asList(Value.ACE, Value.TWO, Value.THREE, Value.FOUR, Value.FIVE, Value.SIX,
                Value.SEVEN, Value.EIGHT, Value.NINE, Value.TEN, Value.JACK, Value.QUEEN, Value.KING);

        List<Card> sortedHand = cardGame.sortHand(hand, null, valueOrder);

        Assertions.assertEquals(hand, sortedHand, "Hand should remain unchanged when suit order list is null");
    }

    @Test
    public void testSortHandWithNullValueOrder() {
        StandardCardGame cardGame = new StandardCardGame();
        List<Card> hand = cardGame.generateHand();
        List<Suit> suitOrder = Arrays.asList(Suit.HEARTS, Suit.DIAMONDS, Suit.CLUBS, Suit.SPADES);

        List<Card> sortedHand = cardGame.sortHand(hand, suitOrder, null);

        Assertions.assertEquals(hand, sortedHand, "Hand should remain unchanged when value order list is null");
    }

    @Test
    public void testSortHandWithIncorrectSizeSuitOrder() {
        StandardCardGame cardGame = new StandardCardGame();
        List<Card> hand = cardGame.generateHand();
        List<Suit> incorrectSuitOrder = Arrays.asList(Suit.HEARTS, Suit.DIAMONDS, Suit.CLUBS);
        List<Value> valueOrder = Arrays.asList(Value.ACE, Value.TWO, Value.THREE, Value.FOUR, Value.FIVE, Value.SIX,
                Value.SEVEN, Value.EIGHT, Value.NINE, Value.TEN, Value.JACK, Value.QUEEN, Value.KING);

        // Verify if the sortHand method throws an exception or handles the incorrect size suit order list appropriately
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            cardGame.sortHand(hand, incorrectSuitOrder, valueOrder);
        });
    }

    @Test
    public void testSortHandWithIncorrectSizeValueOrder() {
        StandardCardGame cardGame = new StandardCardGame();
        List<Card> hand = cardGame.generateHand();
        List<Suit> suitOrder = Arrays.asList(Suit.HEARTS, Suit.DIAMONDS, Suit.CLUBS, Suit.SPADES);
        List<Value> incorrectValueOrder = Arrays.asList(Value.ACE, Value.FIVE, Value.TEN, Value.EIGHT,
                Value.SIX, Value.SEVEN);

        // Verify if the sortHand method throws an exception or handles the incorrect size value order list appropriately
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            cardGame.sortHand(hand, suitOrder, incorrectValueOrder);
        });
    }


}
