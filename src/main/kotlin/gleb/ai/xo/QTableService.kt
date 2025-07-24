package gleb.ai.xo

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service

@Service
class QTableService(
    private val redisTemplate: ReactiveRedisTemplate<String, Any>,
    private val om: ObjectMapper
) {
    companion object {
        private const val Q_TABLE_KEY = "Q_TABLE_KEY"
    }

    suspend fun get(): QTable {
        return redisTemplate.opsForValue()
            .get(Q_TABLE_KEY)
            .cast(QTable::class.java)
            .awaitSingle()
    }

    suspend fun put(qTable: QTable) {
        redisTemplate.opsForValue()
            .set(Q_TABLE_KEY, qTable)
            .awaitSingleOrNull()
    }
}