package interview.blackjack.decks

import interview.blackjack.cards.Card
import interview.blackjack.cards.getAllCards

data class Deck(val cards: List<Card>) {
    companion object {
        fun random() = Deck(getAllCards().shuffled())
        fun empty() = Deck(emptyList())
    }
}
