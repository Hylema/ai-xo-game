package gleb.ai.xo

import org.springframework.stereotype.Component

@Component
class BoardFactory {

    suspend fun create(size: Int): Board {
        return Board(
            board = MutableList<Symbol?>(size * size) { null }
        )
    }
}