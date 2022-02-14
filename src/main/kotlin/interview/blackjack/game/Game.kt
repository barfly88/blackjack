package interview.blackjack.game

class Game(
    private val gameInitializer: GameInitializer,
    private val gameExecutor: GameExecutor,
    private val gameStatePresenter: GameStatePresenter,
) {
    fun run() {
        val initialGameState = gameInitializer.initGame()
        val finalGameState = gameExecutor.playGame(initialGameState)
        gameStatePresenter.presentGameState(finalGameState)
    }
}
