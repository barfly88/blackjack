package interview.blackjack.game

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import interview.blackjack.cards.Card
import interview.blackjack.cards.CardSuit
import interview.blackjack.cards.CardValue
import interview.blackjack.decks.Deck
import interview.blackjack.players.Player
import org.junit.jupiter.api.assertThrows

private val player1 = Player("sam", setOf(Card(CardSuit.SPADES, CardValue.ACE), Card(CardSuit.SPADES, CardValue.V10)))
private val player2 = Player("dealer", setOf(Card(CardSuit.SPADES, CardValue.V10)))

internal class FinalGameStatePresenterTest : StringSpec({

    "should pass formatted strings to the delegate" {
        val result = mutableListOf<String>()
        FinalGameStatePresenter { result.add(it) }.presentGameState(
            GameState(
                remainingDeck = Deck.empty(),
                player1 = player1,
                player2 = player2,
                started = true,
                winner = player1
            )
        )

        result shouldBe listOf("sam", "sam: SA, S10", "dealer: S10")
    }

    "should throw exception when game has no winner yet" {
        assertThrows<IllegalArgumentException> {
            FinalGameStatePresenter { }.presentGameState(
                GameState(
                    remainingDeck = Deck.empty(),
                    player1 = player1,
                    player2 = player2,
                    started = true,
                )
            )
        }
    }
})