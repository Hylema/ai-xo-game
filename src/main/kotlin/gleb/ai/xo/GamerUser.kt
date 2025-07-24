package gleb.ai.xo

data class GamerUser(
    override val symbol: Symbol,
    private val history: List<QValue> = emptyList(),
) : Gamer {
    override suspend fun determineCourse(board: Board): MovePosition {

    }
}
