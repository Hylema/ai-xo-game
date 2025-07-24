package gleb.ai.xo

import org.springframework.stereotype.Component

@Component
class GamerFactory(
    val qTableService: QTableService
) {
    suspend fun ai(symbol: Symbol, isLearning: Boolean = false): Gamer {
        return GamerAI(
            symbol = symbol,
            isLearning = isLearning,
            qTable = qTableService.get().data
        )
    }

    suspend fun user(symbol: Symbol): Gamer {
        return GamerUser(symbol)
    }
}