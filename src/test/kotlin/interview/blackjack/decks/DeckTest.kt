package interview.blackjack.decks

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldNotBe
import interview.blackjack.cards.CardSuit
import interview.blackjack.cards.CardValue

internal class DeckTest : StringSpec({

    "random deck should have 52 different cards" {
        Deck.random().cards shouldHaveSize 52
    }

    "random deck should have random order" {
        Deck.random().cards shouldNotBe Deck.random().cards
    }

    "random deck should have all cards" {
        val randomDeck = Deck.random()

        val groupedBySuit = randomDeck.cards.groupBy { it.suit }
        CardSuit.values().forEach { suit -> groupedBySuit[suit]!! shouldHaveSize 13 }

        val groupedByValue = randomDeck.cards.groupBy { it.value }
        CardValue.values().forEach { value -> groupedByValue[value]!! shouldHaveSize 4 }
    }
})