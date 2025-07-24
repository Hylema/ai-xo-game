package gleb.ai.xo

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.math.sqrt

data class Board(
    private val board: MutableList<Symbol?>
) {
    companion object {
        private val LOCK_MOVE = Mutex()
    }

    suspend fun makeMove(gamer: Gamer) {
        LOCK_MOVE.withLock {
            val movePosition = gamer.determineCourse(this)
            val x = movePosition.x + 1
            val y = movePosition.y + 1
            val currentPositionValue = board[x * y]

            if (currentPositionValue != null)
                throw IllegalArgumentException("Текущая позиция: $movePosition уже имеет значение: $currentPositionValue")

            board[x * y] = gamer.symbol
        }
    }

    fun getBoard(): List<Symbol?> {
        return board.toList()
    }

    fun getSize(): Int {
        return sqrt(board.size.toDouble()).toInt()
    }

    fun toPrimitiveView(): String {
        return board
            .map { it ?: "_" }
            .joinToString("")
    }
}
