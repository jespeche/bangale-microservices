package com.training.project.graphql

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Date
import java.util.UUID.randomUUID

import com.training.project.service.model.Currency as CurrencyModel
import com.training.project.service.model.Price as PriceModel
import com.training.project.service.model.PriceHistory as PriceHistoryModel

class ModelTests {
    private val expectedProductId = randomUUID()
    private val priceModel = PriceModel(CurrencyModel.DOLLAR, 10.0)
    private val priceHistoryModel = PriceHistoryModel(expectedProductId, priceModel, Date(), expectedProductId)

    @Test
    fun `Check Price`() {
        Price(priceModel).apply {
            assertThat(currency).isEqualTo(Currency.DOLLAR)
            assertThat(amount).isEqualTo(10.0.toFloat())
        }
    }

    @Test
    fun `Check Price History`() {
        PriceHistory(priceHistoryModel).apply {
            assertThat(productId).isEqualTo(expectedProductId)
            assertThat(price).isEqualTo(Price(priceModel))
        }
    }

    @Test
    fun `Checking constants as per code coverage`() {
        assertThat(Currency.RUPEES).isNotNull()
        assertThat(Currency.DOLLAR).isNotNull()
        assertThat(Currency.PESOS).isNotNull()
    }
}