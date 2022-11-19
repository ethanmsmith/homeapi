package codes.athenaeum.springql.homeapi.test

import codes.athenaeum.springql.homeapi.DataAccessObject
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest
import org.springframework.context.annotation.Import
import org.springframework.graphql.test.tester.GraphQlTester


@GraphQlTest
@Import(DataAccessObject::class)
class HomeApiApplicationTests(@Autowired private val graphQlTester: GraphQlTester) {
    @Test
    fun `returns Mono greeting`()
    {
        graphQlTester.document("{greetingMono}")
            .execute()
            .path("greetingMono")
            .entity(String::class.java)
            .isEqualTo("Hello, world")
    }

    @Test
    fun greetingsFlux() {
        graphQlTester.document("{greetingsFlux}")
            .execute()
            .path("greetingsFlux")
            .entityList(String::class.java)
            .containsExactly("Hi", "Bonjour", "Hola", "Ciao")
    }
}
