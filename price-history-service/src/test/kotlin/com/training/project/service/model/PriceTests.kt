package com.training.project.service.model

import com.training.project.service.model.Currency.DOLLAR
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PriceTests {

    @Test
    fun `Check Price instantiation`() {
        Price(DOLLAR, 10.0).apply {
            assertThat(currency).isEqualTo(DOLLAR)
            assertThat(amount).isEqualTo(10.0)
        }
    }
}
