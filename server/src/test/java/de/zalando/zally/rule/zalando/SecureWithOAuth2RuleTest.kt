package de.zalando.zally.rule.zalando

import de.zalando.zally.getFixture
import de.zalando.zally.rule.ApiAdapter
import de.zalando.zally.rule.api.Violation
import io.swagger.v3.oas.models.OpenAPI
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SecureWithOAuth2RuleTest {

    private val rule = SecureWithOAuth2Rule()

    private val checkSecurityDefinitionsExpectedOauthViolation = Violation(
            "No OAuth2 security definitions found",
            emptyList())

    private val checkSecurityDefinitionsExpectedHttpsViolation = Violation(
            "OAuth2 should be only used together with https",
            emptyList())

    private val checkPasswordFlowExpectedViolation = Violation(
            "OAuth2 security definitions should use application flow",
            emptyList())

    @Test
    fun checkSecurityDefinitionsWithEmptyReturnsViolation() {
        assertThat(rule.checkSecurityDefinitions(ApiAdapter(OpenAPI()))).isEqualTo(checkSecurityDefinitionsExpectedOauthViolation)
    }

    //TODO rewrite
/*    @Test
    fun checkSecurityDefinitionsWithEmptyDefinitionReturnsViolation() {
        val swagger = Swagger().apply {
            securityDefinitions = emptyMap()
        }
        assertThat(rule.checkSecurityDefinitions(swagger)).isEqualTo(checkSecurityDefinitionsExpectedOauthViolation)
    }

    @Test
    fun checkSecurityDefinitionsWithNoOAuth2ReturnsViolation() {
        val swagger = Swagger().apply {
            securityDefinitions = mapOf(
                "Basic" to BasicAuthDefinition(),
                "ApiKey" to ApiKeyAuthDefinition()
            )
        }
        assertThat(rule.checkSecurityDefinitions(swagger)).isEqualTo(checkSecurityDefinitionsExpectedOauthViolation)
    }

    @Test
    fun checkSecurityDefinitionsWithHttpReturnsViolation() {
        val swagger = Swagger().apply {
            schemes = listOf(Scheme.HTTP, Scheme.HTTPS)
            securityDefinitions = mapOf(
                "Oauth2" to OAuth2Definition()
            )
        }
        assertThat(rule.checkSecurityDefinitions(swagger)).isEqualTo(checkSecurityDefinitionsExpectedHttpsViolation)
    }

    @Test
    fun checkSecurityDefinitionsWIthHttpsReturnsNothing() {
        val swagger = Swagger().apply {
            schemes = listOf(Scheme.HTTPS)
            securityDefinitions = mapOf(
                "Basic" to BasicAuthDefinition(),
                "Oauth2" to OAuth2Definition()
            )
        }
        assertThat(rule.checkSecurityDefinitions(swagger)).isNull()
    }*/

    @Test
    fun checkUsedScopesWithEmpty() {
        assertThat(rule.checkUsedScopes(ApiAdapter(OpenAPI()))).isNull()
    }

    @Test
    fun checkUsedScopesWithoutScope() {
        val swagger = getFixture("api_without_scopes_defined.yaml")
        assertThat(rule.checkUsedScopes(ApiAdapter(swagger))!!.paths).hasSize(4)
    }

    @Test
    fun checkUsedScopesWithDefinedScope() {
        val swagger = getFixture("api_with_defined_scope.yaml")
        assertThat(rule.checkUsedScopes(ApiAdapter(swagger))).isNull()
    }

    @Test
    fun checkUsedScopesWithUndefinedScope() {
        val swagger = getFixture("api_with_undefined_scope.yaml")
        assertThat(rule.checkUsedScopes(ApiAdapter(swagger))!!.paths).hasSize(2)
    }

    @Test
    fun checkUsedScopesWithDefinedAndUndefinedScope() {
        val swagger = getFixture("api_with_defined_and_undefined_scope.yaml")
        assertThat(rule.checkUsedScopes(ApiAdapter(swagger))!!.paths).hasSize(2)
    }

    @Test
    fun checkUsedScopesWithDefinedTopLevelScope() {
        val swagger = getFixture("api_with_toplevel_scope.yaml")
        assertThat(rule.checkUsedScopes(ApiAdapter(swagger))).isNull()
    }

    //TODO rewrite
/*    @Test
    fun checkPasswordFlowShouldReturnNoViolationsWhenNoOauth2Found() {
        val swagger = Swagger().apply {
            securityDefinitions = mapOf(
                "Basic" to BasicAuthDefinition(),
                "ApiKey" to ApiKeyAuthDefinition()
            )
        }
        assertThat(rule.checkPasswordFlow(swagger)).isNull()
    }*/

    //TODO rewrite
    /*@Test
    fun checkPasswordFlowShouldReturnNoViolationsWhenOauth2DefinitionsHasProperFlow() {
        val swagger = Swagger().apply {
            securityDefinitions = mapOf(
                "Basic" to BasicAuthDefinition(),
                "Oauth2" to OAuth2Definition().apply {
                    flow = "application"
                }
            )
        }
        assertThat(rule.checkPasswordFlow(swagger)).isNull()
    }*/

    //TODO rewrite
/*    @Test
    fun checkPasswordFlowShouldReturnViolationsWhenOauth2DefinitionsHasWrongFlow() {
        val swagger = Swagger().apply {
            securityDefinitions = mapOf(
                "Basic" to BasicAuthDefinition(),
                "Oauth2" to OAuth2Definition().apply {
                    flow = "implicit"
                }
            )
        }
        assertThat(rule.checkPasswordFlow(swagger)).isEqualTo(checkPasswordFlowExpectedViolation)
    }
//TODO rewrite
    @Test
    fun checkPasswordFlowShouldReturnViolationsWhenOauth2DefinitionsHasNoFlow() {
        val swagger = Swagger().apply {
            securityDefinitions = mapOf(
                "Basic" to BasicAuthDefinition(),
                "Oauth2" to OAuth2Definition()
            )
        }
        assertThat(rule.checkPasswordFlow(swagger)).isEqualTo(checkPasswordFlowExpectedViolation)
    }
//TODO rewrite
    @Test
    fun checkPasswordFlowShouldReturnViolationsWhenOneOfOauth2DefinitionsIsWrong() {
        val swagger = Swagger().apply {
            securityDefinitions = mapOf(
                "Oauth2A" to OAuth2Definition(),
                "Oauth2B" to OAuth2Definition().apply {
                    flow = "application"
                }
            )
        }
        assertThat(rule.checkPasswordFlow(swagger)).isEqualTo(checkPasswordFlowExpectedViolation)
    }*/
}
