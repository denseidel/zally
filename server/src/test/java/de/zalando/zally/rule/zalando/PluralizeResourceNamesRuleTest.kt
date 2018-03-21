package de.zalando.zally.rule.zalando

import de.zalando.zally.getFixture
import de.zalando.zally.rule.ApiAdapter
import de.zalando.zally.testConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PluralizeResourceNamesRuleTest {

    private val rule = PluralizeResourceNamesRule(testConfig)

    @Test
    fun positiveCase() {
        val swagger = getFixture("pluralizeResourcesValid.json")
        assertThat(rule.validate(ApiAdapter(swagger))).isNull()
    }

    @Test
    fun negativeCase() {
        val swagger = getFixture("pluralizeResourcesInvalid.json")
        val result = rule.validate(ApiAdapter(swagger))!!
        assertThat(result.paths).hasSameElementsAs(listOf("/pet/cats", "/pets/cats/{cat-id}/tail/{tail-id}/strands"))
    }

    @Test
    fun positiveCaseSpp() {
        val swagger = getFixture("api_spp.json")
        assertThat(rule.validate(ApiAdapter(swagger))).isNull()
    }

    @Test
    fun positiveCasePathsWithTheApiPrefix() {
        val swagger = getFixture("spp_with_paths_having_api_prefix.json")
        assertThat(rule.validate(ApiAdapter(swagger))).isNull()
    }

    @Test
    fun negativeCaseTinbox() {
        val swagger = getFixture("api_tinbox.yaml")
        val result = rule.validate(ApiAdapter(swagger))!!
        assertThat(result.paths).hasSameElementsAs(listOf("/queue/configs/{config-id}", "/queue/models",
            "/queue/models/{model-id}", "/queue/summaries"))
    }
}
