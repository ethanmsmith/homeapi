package codes.athenaeum.springql.homeapi

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

@Repository
class DataAccessObject {
    fun getBasic():String {
        return "basic enthusiasm"
    }
    fun greetingMono():Mono<String> {
        return Mono.just("Hello, world")
    }
    fun greetingsFlux(): Flux<String> {
        return Flux.just("Hi","Bonjour","Hola","Ciao")
    }
    fun helloNumber(): Mono<Int> {
        return Mono.just(42)
    }
}