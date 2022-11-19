package codes.athenaeum.springql.homeapi

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Controller
class MainMapper(private val dao: DataAccessObject) {

    @QueryMapping
    public fun helloWorld(): String {
        return dao.getBasic()
    }

    @QueryMapping
    public fun greetingsFlux() : Flux<String> {
        return dao.greetingsFlux()
    }

    @QueryMapping
    public fun greetingMono() : Mono<String> {
        return dao.greetingMono()
    }

    @QueryMapping
    public fun helloNumber() : Mono<Int> {
        return dao.helloNumber()
    }
}