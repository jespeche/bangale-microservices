package com.training.project.service.model

import com.training.project.service.model.Currency.DOLLAR
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID

class PriceHistoryTests {

    private val expectedProductId = randomUUID()
    private val expectedPrice = Price(DOLLAR, 10.0)

    @Test
    fun `Check PriceHistory instantiation`() {
        PriceHistory(expectedProductId, expectedPrice).apply {
            assertThat(productId).isEqualTo(expectedProductId)
            assertThat(price).isEqualTo(expectedPrice)
        }
    }
}
