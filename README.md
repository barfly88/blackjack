# Blackjack interview task

This is an implementation of Blackjack game in Kotlin. 
Gradle is used only for dependency management of testing library (kotest) and testing platform (jUnit).
The main code does not use any third party dependencies other than the standard Kotlin library i.e. can be compiled and run without Gradle.

## Run

To run the game with a deck of cards defined in a file, use the following command:

`./gradlew run --args='path to deck definition'`

To start a game with a randomly shuffled deck, run the following command:

`./gradlew run`

The game can be also run without gradle (e.g. using IDE). The entrypoint of the game is the main method of `BlackJackGame.kt`.

## Testing

To run unit and integration tests, use the following command:

`./gradlew run`