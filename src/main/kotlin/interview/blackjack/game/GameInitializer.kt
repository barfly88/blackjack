package interview.blackjack.game

import interview.blackjack.decks.Deck
import interview.blackjack.players.Player

data class GameState(
    val remainingDeck: Deck,
    val player1: Player,
    val player2: Player,
    val started: Boolean = false,
    val winner: Player? = null
) {
    fun isFinished() = remainingDeck.cards.isEmpty() || winner != null
}

interface GameInitializer {
    fun initGame(): GameState
}

class SamAndDealerGameInitializer(private val deck: Deck) : GameInitializer {
    override fun initGame() = with(deck) {
        if (cards.size < 4) throw NotEnoughCardsException()
        GameState(
            copy(cards = cards.drop(4)),
            Player("sam", setOf(cards[0], cards[2])),
            Player("dealer", setOf(cards[1], cards[3]))
        )
    }
}

class NotEnoughCardsException : Exception()