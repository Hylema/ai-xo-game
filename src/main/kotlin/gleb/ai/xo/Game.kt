package gleb.ai.xo

import org.springframework.stereotype.Component

@Component
class Game(
    private val appProps: AppProps,
    private val boardFactory: BoardFactory
) {
    suspend fun start(gamer1: Gamer, gamer2: Gamer): String {
        if (gamer1.symbol == gamer2.symbol)
            throw IllegalArgumentException("Нельзя начать игру с одинаковыми символами")

        val (x, o) = when {
            gamer1.symbol == Symbol.X -> Pair(gamer1, gamer2)
            else -> Pair(gamer2, gamer1)
        }

        val board = boardFactory.create(appProps.boardSize)

        var playerTurn = x.symbol
        while (getStatusOfBoard(board) == GameStatus.PROCESSING) {
            val currentGamerTurn = when (playerTurn) {
                Symbol.X -> x
                else -> o
            }

            board.makeMove(currentGamerTurn)

            playerTurn = currentGamerTurn.symbol.getReverseSymbol()
        }

        val (winner, losser) = when (getStatusOfBoard(board)) {
            GameStatus.WIN_O -> Pair(o, x)
            GameStatus.WIN_X -> Pair(x, o)
            else -> throw IllegalStateException()
        }


    }

    private suspend fun getStatusOfBoard(board: Board): GameStatus {
        return GameUtils.getStatus(board)
    }
}