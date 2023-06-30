package com.atexo.cards.controllers;

import com.atexo.cards.models.GameResponse;
import com.atexo.cards.models.enums.Suit;
import com.atexo.cards.models.enums.Value;
import com.atexo.cards.models.interfaces.Card;
import com.atexo.cards.services.StandardCardGame;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Cards Game Atexo", description = "The Annex Card Game")
@RestController
@RequestMapping("/api/v1/game")
public class CardController {
    private final StandardCardGame cardGame;

    public CardController(StandardCardGame cardGame) {
        this.cardGame = cardGame;
    }
    
    @Operation(
        summary = "Provides two hands of a card game, where one hand is ordered and the other is not.",
        description = "This path provides the two hands of cards according to the game's rules.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = GameResponse.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/hand")
    public GameResponse playGame() {
        List<Card> hand = cardGame.generateHand();
        System.out.println("Unsorted Hand: ");
        hand.forEach(System.out::println);

        List<Suit> suitOrder = cardGame.generateRandomSuitOrder();
        System.out.println("Suit Order: ");
        suitOrder.forEach(System.out::println);

        List<Value> valueOrder = cardGame.generateRandomValueOrder();
        System.out.println("Value Order: ");
        valueOrder.forEach(System.out::println);

        List<Card> sortedHand = cardGame.sortHand(hand, suitOrder, valueOrder);
        System.out.println("Hand sorted: ");
        sortedHand.forEach(System.out::println);

        return new GameResponse(hand, sortedHand);
    }
}
