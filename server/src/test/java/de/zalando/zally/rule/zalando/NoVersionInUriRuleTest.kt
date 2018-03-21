package de.zalando.zally.rule.zalando

import de.zalando.zally.rule.ApiAdapter
import de.zalando.zally.rule.api.Violation
import io.swagger.v3.oas.models.OpenAPI
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class NoVersionInUriRuleTest {

    private val rule = NoVersionInUriRule()

    val expectedViolation = Violation(
            "basePath attribute contains version number",
            emptyList())

    //TODO rewrite or delete. There is no basePath anymore
//    @Test
//    fun returnsViolationsWhenVersionIsInTheBeginingOfBasePath() {
//        val swagger = Swagger().apply { basePath = "/v1/tests" }
//        assertThat(rule.validate(ApiAdapter(swagger))).isEqualTo(expectedViolation)
//    }

    //TODO rewrite or delete. There is no basePath anymore
/*    @Test
    fun returnsViolationsWhenVersionIsInTheMiddleOfBasePath() {
        val swagger = Swagger().apply { basePath = "/api/v1/tests" }
        assertThat(rule.validate(swagger)).isEqualTo(expectedViolation)
    }*/

    //TODO rewrite or delete. There is no basePath anymore
/*
    @Test
    fun returnsViolationsWhenVersionIsInTheEndOfBasePath() {
        val swagger = Swagger().apply { basePath = "/api/v1" }
        assertThat(rule.validate(swagger)).isEqualTo(expectedViolation)
    }
*/

    //TODO rewrite or delete. There is no basePath anymore
/*
    @Test
    fun returnsViolationsWhenVersionIsBig() {
        val swagger = Swagger().apply { basePath = "/v1024/tests" }
        assertThat(rule.validate(swagger)).isEqualTo(expectedViolation)
    }
*/

    //TODO rewrite or delete. There is no basePath anymore
/*
    @Test
    fun returnsEmptyViolationListWhenNoVersionFoundInURL() {
        val swagger = Swagger().apply { basePath = "/violations/" }
        assertThat(rule.validate(swagger)).isNull()
    }
*/

    @Test
    fun returnsEmptyViolationListWhenBasePathIsNull() {
        val swagger = OpenAPI()
        assertThat(rule.validate(ApiAdapter(swagger))).isNull()
    }
}
