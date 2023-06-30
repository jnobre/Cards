# Card Game 

The Standard Card Game is a Java library that provides functionality for generating hands of playing cards, shuffling and sorting cards based on custom orders, and performing other card-related operations.


## Game Rules 

In this game, a player randomly draws a hand of 10 cards.
Each card has a suit ("Diamonds", for example) and a value ("10", for example).
You are asked to:

Construct a random order of suits. The order of the suits is, for example, one of the following:

-  Diamonds, Hearts, Spades, Clubs

Construct a random order of values. The order of values is, for example, one of the following:

- Ace, 5, 10, 8, 6, 7, 4, 2, 3, 9, Queen, King, Jack

Build a hand of 10 cards at random.

Show the "unsorted" hand on the screen, then the sorted hand in any order defined in steps 1 and 2. That is, you must sort the cards by suit and value.

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