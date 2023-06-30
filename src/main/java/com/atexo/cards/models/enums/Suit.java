package com.atexo.cards.models.enums;

public enum Suit {
    DIAMONDS("Diamonds"),
    HEARTS("Hearts"),
    SPADES("Spades"),
    CLUBS("Clubs");

    private final String name;

    Suit(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
