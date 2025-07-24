package gleb.ai.xo

data class GamerAI(
    override val symbol: Symbol,
    val isLearning: Boolean,
    val qTable: Map<String, List<QValue>>,
    private val history: List<Pair<String, QValue>> = emptyList(),
) : Gamer {
    override suspend fun determineCourse(board: Board): MovePosition {
        val key = board.toPrimitiveView()
        val qValues = qTable[key]

        val qValue = when (isLearning) {
            true -> qValues.random()
            false -> qValues.maxBy { it.weight }
        }

        history.plus(Pair(key, qValue))

        return qValue.movePosition
    }
}
