package gleb.ai.xo

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app")
data class AppProps(
    val boardSize: Int,
    val aiLearning: Boolean,
    val userSymbol: Symbol
)
