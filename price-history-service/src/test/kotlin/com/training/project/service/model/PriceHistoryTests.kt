package com.training.project.service.model

import com.training.project.service.model.Currency.DOLLAR
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.UUID

class PriceHistoryTests {

    @Test
    fun `Check PriceHistory instantiation`() {
        PriceHistory(UUID.randomUUID(), Price(DOLLAR,10.0)).apply {
            assertThat(price.currency).isEqualTo(DOLLAR)
            assertThat(price.amount).isEqualTo(10.0)
        }
    }

}
