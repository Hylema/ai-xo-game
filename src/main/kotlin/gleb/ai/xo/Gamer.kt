package gleb.ai.xo

import java.util.UUID

interface Gamer {
    val id: String
        get() = UUID.randomUUID().toString()
    val symbol: Symbol
    suspend fun determineCourse(board: Board): MovePosition
}