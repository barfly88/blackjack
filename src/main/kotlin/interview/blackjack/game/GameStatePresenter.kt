package interview.blackjack.game

import interview.blackjack.cards.Card
import interview.blackjack.cards.CardValue
import interview.blackjack.players.Player

interface GameStatePresenter {
    fun presentGameState(gameState: GameState)
}

class FinalGameStatePresenter(private val delegate: (String) -> Unit) : GameStatePresenter {

    override fun presentGameState(gameState: GameState) {
        delegate(gameState.winner?.name ?: throw IllegalArgumentException())
        delegate(gameState.player1.asString())
        delegate(gameState.player2.asString())
    }

    private fun Player.asString() = "$name: ${hand.joinToString { it.getSymbol() }}"

    private fun Card.getSymbol() = suit.name[0] + when (value) {
        CardValue.JACK -> "J"
        CardValue.QUEEN -> "Q"
        CardValue.KING -> "K"
        CardValue.ACE -> "A"
        else -> value.score.toString()
    }
}

