package interview.blackjack.game

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import interview.blackjack.cards.Card
import interview.blackjack.cards.CardSuit.*
import interview.blackjack.cards.CardValue.*
import interview.blackjack.decks.Deck
import interview.blackjack.players.Player

private val gameplay = ClassicBlackJackGameExecutor()

internal class ClassicBlackJackGameplayTest : StringSpec({

    "sam wins when he starts with Blackjack" {
        val gameState = GameState(
            Deck(listOf()),
            Player("sam", setOf(Card(SPADES, ACE), Card(SPADES, V10))),
            Player("dealer", setOf(Card(CLUBS, ACE), Card(CLUBS, V4)))
        )
        gameplay.playGame(gameState).winner?.name shouldBe "sam"
    }

    "sam wins when both players starts with Blackjack" {
        val gameState = GameState(
            Deck(listOf()),
            Player("sam", setOf(Card(SPADES, ACE), Card(SPADES, V10))),
            Player("dealer", setOf(Card(CLUBS, ACE), Card(CLUBS, V10)))
        )
        gameplay.playGame(gameState).winner?.name shouldBe "sam"
    }

    "dealer wins when he has Blackjack" {
        val gameState = GameState(
            Deck(listOf()),
            Player("sam", setOf(Card(SPADES, V6), Card(HEARTS, V10))),
            Player("dealer", setOf(Card(CLUBS, ACE), Card(DIAMONDS, V10)))
        )
        gameplay.playGame(gameState).winner?.name shouldBe "dealer"
    }

    "dealer wins when both players starts with 22" {
        val gameState = GameState(
            Deck(listOf()),
            Player("sam", setOf(Card(SPADES, ACE), Card(HEARTS, ACE))),
            Player("dealer", setOf(Card(CLUBS, ACE), Card(DIAMONDS, ACE)))
        )
        gameplay.playGame(gameState).winner?.name shouldBe "dealer"
    }

    "sam must stop drawing cards from the deck if their total reaches 17 or higher" {
        val gameState = GameState(
            Deck(listOf(Card(SPADES, V2), Card(DIAMONDS, V8))),
            Player("sam", setOf(Card(SPADES, V5), Card(HEARTS, V10))),
            Player("dealer", setOf(Card(CLUBS, ACE), Card(DIAMONDS, V2)))
        )
        with(gameplay.playGame(gameState)) {
            player1.hand shouldHaveSize 3
            player2.hand shouldHaveSize 3
        }
    }

    "sam must stop drawing cards from the deck if their total reaches higher than 17" {
        val gameState = GameState(
            Deck(listOf(Card(SPADES, V2), Card(DIAMONDS, V8))),
            Player("sam", setOf(Card(SPADES, V6), Card(HEARTS, V10))),
            Player("dealer", setOf(Card(CLUBS, ACE), Card(DIAMONDS, V2)))
        )
        with(gameplay.playGame(gameState)) {
            player1.hand shouldHaveSize 3
            player2.hand shouldHaveSize 3
        }
    }

    "sam has lost the game if their total is higher than 21" {
        val gameState = GameState(
            Deck(listOf(Card(HEARTS, V10))),
            Player("sam", setOf(Card(SPADES, V10), Card(HEARTS, V2))),
            Player("dealer", setOf(Card(CLUBS, ACE), Card(DIAMONDS, V2)))
        )
        gameplay.playGame(gameState).winner?.name shouldBe "dealer"
    }

    "the dealer must stop drawing cards when their total is higher than sam" {
        val gameState = GameState(
            Deck(listOf(Card(CLUBS, V3), Card(CLUBS, V4))),
            Player("sam", setOf(Card(SPADES, V10), Card(HEARTS, V7))),
            Player("dealer", setOf(Card(CLUBS, V10), Card(DIAMONDS, V7)))
        )
        with(gameplay.playGame(gameState)) {
            player1.hand shouldHaveSize 2
            player2.hand shouldHaveSize 3
        }
    }

    "the dealer has lost the game if their total is higher than 21" {
        val gameState = GameState(
            Deck(listOf()),
            Player("sam", setOf(Card(SPADES, V10), Card(HEARTS, V7))),
            Player("dealer", setOf(Card(CLUBS, ACE), Card(DIAMONDS, ACE)))
        )
        gameplay.playGame(gameState).winner?.name shouldBe "sam"
    }

    "determine which player wins the game (sam case)" {
        val gameState = GameState(
            Deck(listOf(Card(SPADES, V4), Card(DIAMONDS, V3))),
            Player("sam", setOf(Card(SPADES, V10), Card(HEARTS, V6))),
            Player("dealer", setOf(Card(CLUBS, V10), Card(DIAMONDS, V6)))
        )
        gameplay.playGame(gameState).winner?.name shouldBe "sam"
    }

    "determine which player wins the game (dealer case)" {
        val gameState = GameState(
            Deck(listOf(Card(SPADES, V4), Card(DIAMONDS, V5))),
            Player("sam", setOf(Card(SPADES, V10), Card(HEARTS, V6))),
            Player("dealer", setOf(Card(CLUBS, V10), Card(DIAMONDS, V6)))
        )
        gameplay.playGame(gameState).winner?.name shouldBe "dealer"
    }
})