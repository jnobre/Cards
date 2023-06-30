package com.atexo.cards.models.enums;

public enum Value {
    ACE("Ace"),
    FIVE("5"),
    TEN("10"),
    EIGHT("8"),
    SIX("6"),
    SEVEN("7"),
    FOUR("4"),
    TWO("2"),
    THREE("3"),
    NINE("9"),
    QUEEN("Queen"),
    KING("King"),
    JACK("Jack");

    private final String name;

    Value(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
