package interview.blackjack.game

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import interview.blackjack.cards.parseCard
import interview.blackjack.decks.Deck
import interview.blackjack.decks.parseDeck
import interview.blackjack.players.Player

private class PresenterThatTestsGameState(private val expected: GameState) : GameStatePresenter {
    override fun presentGameState(gameState: GameState) = gameState shouldBe expected
}

class ClassicBlackJackIntegrationTest : StringSpec({

    "should find a winner in classic black jack" {

        val testDeck = parseDeck("CA,D5,H9,HQ,S8".split(","))

        val sam = Player("sam", setOf(parseCard("CA"), parseCard("H9")))

        val expectedGameResult = GameState(
            player1 = sam,
            player2 = Player("dealer", setOf(parseCard("D5"), parseCard("HQ"), parseCard("S8"))),
            remainingDeck = Deck.empty(),
            winner = sam,
            started = true
        )

        Game(
            gameInitializer = SamAndDealerGameInitializer(deck = testDeck),
            gameExecutor = ClassicBlackJackGameExecutor(),
            gameStatePresenter = PresenterThatTestsGameState(expectedGameResult)
        ).run()
    }

})

