package codes.athenaeum.springql.homeapi.security

import codes.athenaeum.springql.homeapi.MainMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import org.springframework.web.reactive.socket.server.WebSocketService
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService
import org.springframework.web.reactive.socket.server.upgrade.TomcatRequestUpgradeStrategy
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import reactor.core.publisher.Mono

class MyWebSocketHandler : WebSocketHandler {
    override fun handle(session: WebSocketSession): Mono<Void> {

        val output = session.receive()
            .doOnNext {
                println(it)
            }
            .map { session.textMessage("Echo $it") }

        return session.send(output)
    }
}

@Configuration
class WebConfig {
    @Bean
    fun handlerMapping(): HandlerMapping {
        val map = mapOf("/api" to MyWebSocketHandler())
        val order = -1 // before annotated controllers

        return SimpleUrlHandlerMapping(map, order)
    }

    @Bean
    fun webSocketService(): WebSocketService {
        val strategy = TomcatRequestUpgradeStrategy().apply {
            setMaxSessionIdleTimeout(100000L)
        }
        return HandshakeWebSocketService(strategy)
    }
}