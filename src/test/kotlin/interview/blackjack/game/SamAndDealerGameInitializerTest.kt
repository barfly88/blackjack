package interview.blackjack.game

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import interview.blackjack.cards.Card
import interview.blackjack.cards.CardSuit
import interview.blackjack.cards.CardValue
import interview.blackjack.decks.Deck
import interview.blackjack.players.Player

internal class SamAndDealerGameInitializerTest : StringSpec({

    "should create Sam and Dealer and deal cards alternately" {
        val firstCard = Card(CardSuit.SPADES, CardValue.QUEEN)
        val secondCard = Card(CardSuit.DIAMONDS, CardValue.QUEEN)
        val thirdCard = Card(CardSuit.CLUBS, CardValue.QUEEN)
        val fourthCard = Card(CardSuit.HEARTS, CardValue.QUEEN)

        val deck = Deck(listOf(firstCard, secondCard, thirdCard, fourthCard))

        SamAndDealerGameInitializer(deck).initGame() shouldBe GameState(
            Deck.empty(),
            Player("sam", setOf(firstCard, thirdCard)),
            Player("dealer", setOf(secondCard, fourthCard))
        )
    }

    "should throw exception when initial deck has not enough cards" {
        val firstCard = Card(CardSuit.SPADES, CardValue.QUEEN)
        val secondCard = Card(CardSuit.DIAMONDS, CardValue.QUEEN)
        val thirdCard = Card(CardSuit.CLUBS, CardValue.QUEEN)

        val deck = Deck(listOf(firstCard, secondCard, thirdCard))

        shouldThrow<NotEnoughCardsException> {
            SamAndDealerGameInitializer(deck).initGame()
        }
    }

})