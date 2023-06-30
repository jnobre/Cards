# Card Game 

The Standard Card Game is a Java library that provides functionality for generating hands of playing cards, shuffling and sorting cards based on custom orders, and performing other card-related operations.


## Game Rules 

In this card game, a player will randomly draw a hand of 10 cards. Each card consists of a suit (e.g., "Diamonds") and a value (e.g., "10"). The objective is to construct a hand of cards and display both the unsorted and sorted versions of the hand.

The game follows the following rules:

1. Construct a random order of suits from the following options:
   - Diamonds, Hearts, Spades, Clubs
3. Construct a random order of values from the following options:
   - Ace, 5, 10, 8, 6, 7, 4, 2, 3, 9, Queen, King, Jack 
5. Build a hand of 10 cards by randomly selecting cards from the available suits and values. 
6. Display the unsorted hand on the screen, showing the cards in the order they were drawn. 
7. Sort the hand of cards by suit and value using the custom order defined in steps 1 and 2. 
8. Display the sorted hand on the screen, showing the cards in any order defined by the custom suit and value order.

By following these rules, players can experience the randomness of drawing a hand of cards and observe the sorting of the hand based on suit and value.

Feel free to make any further adjustments or additions to the text as needed.

## Features

- Generate a random hand of playing cards.
- Shuffle a deck of cards.
- Sort a hand of cards based on custom suit and value orders.

## Installation

To use the Standard Card Game library in your Maven project, you need to have Maven installed.

To compile the project, open a terminal or command prompt in the project directory and run the following command:

<code>mvn clean install</code>

To run the program, use the following command:

<code>mvn spring-boot:run</code>

## Usage

The Standard Card Game project includes a Swagger UI interface that allows you to interact with the API. After running the program using the mvn spring-boot:run command, you can access the Swagger UI at the following URL: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) .

The API endpoint for generating a hand of cards is `/api/v1/game/hand`

When you execute the API endpoint, the results will be displayed in the console.

Make sure to check the console output for the generated hand of cards when using the API.

## Contributing

Contributions to the Standard Card Game project are welcome! If you find any issues or have suggestions for improvements, please feel free to open an issue or submit a pull request.

When contributing code, please ensure that you follow the existing coding style and conventions, write appropriate tests, and provide clear documentation.

## Contact

For any questions, suggestions, or inquiries, please contact <joaoanobre@gmail.com>