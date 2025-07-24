package gleb.ai.xo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(AppProps::class)
@SpringBootApplication
class XoApplication

fun main(args: Array<String>) {
    runApplication<XoApplication>(*args)
}
