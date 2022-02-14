package interview.blackjack.game

import interview.blackjack.cards.Card
import interview.blackjack.decks.Deck
import interview.blackjack.players.Player

interface GameExecutor {
    fun playGame(gameState: GameState): GameState
}

private const val BLACKJACK_SCORE = 21
private const val DEALER_INITIAL_WINNING_SCORE = 22
private const val PLAYER_1_PULLING_LIMIT_SCORE = 17

class ClassicBlackJackGameExecutor : GameExecutor {

    override fun playGame(gameState: GameState): GameState = with(gameState) {
        when {
            bothPlayersHave22Initially() || player1.exceedsBlackJack() -> copy(winner = player2)
            player1HasBlackjackInitially() || player2.exceedsBlackJack() -> copy(winner = player1)
            !started -> copy(started = true)
            player1CanPullCards() -> addCardToPlayer1Hand()
            player2CanPullCards() -> addCardToPlayer2Hand()
            else -> copy(winner = if (player1HasBiggerScore()) player1 else player2)
        }
    }.let { if (it.winner == null) playGame(it) else it }

    private fun GameState.addCardToPlayer2Hand() = copy(
        remainingDeck = remainingDeck.withoutFirstCard(),
        player2 = player2.withNewCard(remainingDeck.cards.first())
    )

    private fun GameState.addCardToPlayer1Hand() = copy(
        remainingDeck = remainingDeck.withoutFirstCard(),
        player1 = player1.withNewCard(remainingDeck.cards.first())
    )

    private fun GameState.player1HasBiggerScore() = player1.getHandScore() > player2.getHandScore()

    private fun Player.exceedsBlackJack() = getHandScore() > interview.blackjack.game.BLACKJACK_SCORE

    private fun GameState.player2CanPullCards() = !isFinished()
            && player1.getHandScore() >= player2.getHandScore()

    private fun GameState.player1CanPullCards() = !isFinished()
            && player1.getHandScore() < PLAYER_1_PULLING_LIMIT_SCORE

    private fun GameState.bothPlayersHave22Initially() = !started
            && player1.getHandScore() == DEALER_INITIAL_WINNING_SCORE
            && player1.getHandScore() == player2.getHandScore()

    private fun GameState.player1HasBlackjackInitially() = !started
            && player1.getHandScore() == BLACKJACK_SCORE

    private fun Player.getHandScore() = hand.sumOf { card -> card.value.score }

    private fun Player.withNewCard(card: Card) = copy(hand = hand + card)

    private fun Deck.withoutFirstCard() = copy(cards = cards.drop(1))

}
