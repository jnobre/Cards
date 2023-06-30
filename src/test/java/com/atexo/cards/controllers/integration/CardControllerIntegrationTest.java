package com.atexo.cards.controllers.integration;

import com.atexo.cards.controllers.CardController;
import com.atexo.cards.models.PlayingCard;
import com.atexo.cards.models.enums.Suit;
import com.atexo.cards.models.enums.Value;
import com.atexo.cards.models.interfaces.Card;
import com.atexo.cards.services.StandardCardGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.MockitoAnnotations;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Arrays;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;;

@WebMvcTest(CardController.class)
public class CardControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StandardCardGame cardGame;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPlayGame_ShouldReturnGameResponse() throws Exception {
        // Given
        List<Card> hand = Arrays.asList(
                new PlayingCard(Suit.HEARTS, Value.FIVE),
                new PlayingCard(Suit.DIAMONDS, Value.THREE),
                new PlayingCard(Suit.CLUBS, Value.FOUR),
                new PlayingCard(Suit.DIAMONDS, Value.SEVEN),
                new PlayingCard(Suit.HEARTS, Value.NINE),
                new PlayingCard(Suit.DIAMONDS, Value.EIGHT),
                new PlayingCard(Suit.HEARTS, Value.FOUR),
                new PlayingCard(Suit.SPADES, Value.FOUR),
                new PlayingCard(Suit.HEARTS, Value.ACE),
                new PlayingCard(Suit.CLUBS, Value.TEN)
        );

        List<Suit> suitOrder = Arrays.asList(Suit.HEARTS, Suit.DIAMONDS, Suit.CLUBS, Suit.SPADES);
        List<Value> valueOrder = Arrays.asList(Value.NINE, Value.KING, Value.FOUR, Value.THREE, Value.EIGHT,
                Value.TEN, Value.JACK, Value.ACE, Value.FIVE, Value.SEVEN, Value.QUEEN, Value.SIX, Value.TWO);

        List<Card> sortedHand = Arrays.asList(
                new PlayingCard(Suit.DIAMONDS, Value.THREE),
                new PlayingCard(Suit.DIAMONDS, Value.EIGHT),
                new PlayingCard(Suit.DIAMONDS, Value.SEVEN),
                new PlayingCard(Suit.HEARTS, Value.NINE),
                new PlayingCard(Suit.HEARTS, Value.FOUR),
                new PlayingCard(Suit.HEARTS, Value.ACE),
                new PlayingCard(Suit.HEARTS, Value.FIVE),
                new PlayingCard(Suit.CLUBS, Value.FOUR),
                new PlayingCard(Suit.CLUBS, Value.TEN),
                new PlayingCard(Suit.SPADES, Value.FOUR)
        );

        given(cardGame.generateHand()).willReturn(hand);
        given(cardGame.generateRandomSuitOrder()).willReturn(suitOrder);
        given(cardGame.generateRandomValueOrder()).willReturn(valueOrder);
        given(cardGame.sortHand(hand, suitOrder, valueOrder)).willReturn(sortedHand);

        // When-Then
        mockMvc.perform(get("/api/v1/game/hand"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hand").isArray())
                .andExpect(jsonPath("$.hand.length()").value(hand.size()))
                .andExpect(jsonPath("$.sortedHand").isArray())
                .andExpect(jsonPath("$.sortedHand.length()").value(sortedHand.size()))
                .andExpect(jsonPath("$.sortedHand[0].suit").value(sortedHand.get(0).suit().toString()))
                .andExpect(jsonPath("$.sortedHand[0].value").value(sortedHand.get(0).value().toString()))
                .andExpect(jsonPath("$.sortedHand[1].suit").value(sortedHand.get(1).suit().toString()))
                .andExpect(jsonPath("$.sortedHand[1].value").value(sortedHand.get(1).value().toString()))
                .andExpect(jsonPath("$.sortedHand[2].suit").value(sortedHand.get(2).suit().toString()))
                .andExpect(jsonPath("$.sortedHand[2].value").value(sortedHand.get(2).value().toString()))
                .andExpect(jsonPath("$.sortedHand[3].suit").value(sortedHand.get(3).suit().toString()))
                .andExpect(jsonPath("$.sortedHand[3].value").value(sortedHand.get(3).value().toString()))
                .andExpect(jsonPath("$.sortedHand[4].suit").value(sortedHand.get(4).suit().toString()))
                .andExpect(jsonPath("$.sortedHand[4].value").value(sortedHand.get(4).value().toString()))
                .andExpect(jsonPath("$.sortedHand[5].suit").value(sortedHand.get(5).suit().toString()))
                .andExpect(jsonPath("$.sortedHand[5].value").value(sortedHand.get(5).value().toString()))
                .andExpect(jsonPath("$.sortedHand[6].suit").value(sortedHand.get(6).suit().toString()))
                .andExpect(jsonPath("$.sortedHand[6].value").value(sortedHand.get(6).value().toString()))
                .andExpect(jsonPath("$.sortedHand[7].suit").value(sortedHand.get(7).suit().toString()))
                .andExpect(jsonPath("$.sortedHand[7].value").value(sortedHand.get(7).value().toString()))
                .andExpect(jsonPath("$.sortedHand[8].suit").value(sortedHand.get(8).suit().toString()))
                .andExpect(jsonPath("$.sortedHand[8].value").value(sortedHand.get(8).value().toString()))
                .andExpect(jsonPath("$.sortedHand[9].suit").value(sortedHand.get(9).suit().toString()))
                .andExpect(jsonPath("$.sortedHand[9].value").value(sortedHand.get(9).value().toString()))
                .andDo(print());
    }

}