package interview.blackjack

import interview.blackjack.decks.Deck
import interview.blackjack.decks.parseDeck
import interview.blackjack.decks.readDeckDefinitionFromFile
import interview.blackjack.game.ClassicBlackJackGameExecutor
import interview.blackjack.game.FinalGameStatePresenter
import interview.blackjack.game.Game
import interview.blackjack.game.SamAndDealerGameInitializer

fun main(args: Array<String>) = Game(
    gameInitializer = SamAndDealerGameInitializer(deck = prepareDeck(args)),
    gameExecutor = ClassicBlackJackGameExecutor(),
    gameStatePresenter = FinalGameStatePresenter(::println)
).run()

private fun prepareDeck(args: Array<String>) = when {
    args.size == 1 -> parseDeck(readDeckDefinitionFromFile(args.first()))
    args.isEmpty() -> Deck.random()
    else -> throw IllegalArgumentException(
        "Program arguments should have 1 element (path to the deck definition file) or be empty (random deck)."
    )
}
