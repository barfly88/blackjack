package interview.blackjack.players

import interview.blackjack.cards.Card

data class Player(val name: String, val hand: Set<Card> = setOf())
