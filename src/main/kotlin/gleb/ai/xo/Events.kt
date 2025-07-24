package gleb.ai.xo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class Events(
    private val game: Game,
    private val gamerFactory: GamerFactory,
    private val appProps: AppProps
) {

    companion object {
        val PARALLEL_COUNT = Runtime.getRuntime().availableProcessors() * 2
    }

    @EventListener(ApplicationReadyEvent::class)
    fun appStart() = runBlocking {
        if (appProps.aiLearning) {
            val gamerX = gamerFactory.ai(Symbol.X, isLearning = true)
            val gamerO = gamerFactory.ai(Symbol.O, isLearning = true)

            flow { while (true) emit(Unit) }
                .buffer(PARALLEL_COUNT)
                .onEach { game.start(gamerX, gamerO) }
                .launchIn(CoroutineScope(Dispatchers.Default))
        } else {
            val gamerUser = gamerFactory.user(appProps.userSymbol)
            val gamerAI = gamerFactory.ai(appProps.userSymbol.getReverseSymbol())

            game.start(gamerUser, gamerAI)
        }
    }
}