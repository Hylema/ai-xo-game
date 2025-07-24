package gleb.ai.xo

import kotlin.math.sqrt

object GameUtils {
    fun getStatus(board: Board): GameStatus {
        return status(board.getBoard())
    }

    private fun status(board: List<Symbol?>): GameStatus {
        val size = sqrt(board.size.toDouble()).toInt()
        require(size * size == board.size) { "Доска должна быть квадратной (например, 3x3, 10x10)" }

        // Проверка строк и столбцов
        for (i in 0 until size) {
            // Проверка i-й строки
            val rowWinner = checkLine(board, i * size, 1, size)
            if (rowWinner != null) return rowWinner

            // Проверка i-го столбца
            val colWinner = checkLine(board, i, size, size)
            if (colWinner != null) return colWinner
        }

        // Проверка диагоналей (если нужно)
        val diag1Winner = checkLine(board, 0, size + 1, size)
        if (diag1Winner != null) return diag1Winner

        val diag2Winner = checkLine(board, size - 1, size - 1, size)
        if (diag2Winner != null) return diag2Winner

        return GameStatus.PROCESSING
    }

    private fun checkLine(board: List<Symbol?>, start: Int, step: Int, size: Int): Symbol? {
        val first = board[start]
        if (first == null) return null

        for (i in 1 until size) {
            val index = start + i * step
            if (index >= board.size || board[index] != first) {
                return null
            }
        }

        return first
    }
}