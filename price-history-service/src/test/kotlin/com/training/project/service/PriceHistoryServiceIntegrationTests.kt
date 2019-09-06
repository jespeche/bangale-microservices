package com.training.project.service

import com.training.project.service.implementation.PriceHistoryServiceImpl
import com.training.project.service.model.Currency.PESOS
import com.training.project.service.model.PriceHistory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.UUID.randomUUID

@ExtendWith(SpringExtension::class)
@DataJpaTest
@EnableAutoConfiguration
@ContextConfiguration(classes = [PriceHistoryServiceImpl::class])
class PriceHistoryServiceIntegrationTests(@Autowired val service: PriceHistoryService) {

    private lateinit var priceHistory: PriceHistory
    private val expectedProductId = randomUUID()

    @BeforeEach
    fun setUp() {
        priceHistory = service.registerPrice(expectedProductId, PESOS, 50.0)
    }

    @Test
    fun `Check Price registration`() {
        assertThat(service.registerPrice(expectedProductId, PESOS, 50.0)).isNotNull
    }

    @Test
    fun `Check attributes of registered products`() {
        service.registerPrice(expectedProductId, PESOS, 50.0).apply {
            assertThat(productId).isEqualTo(expectedProductId)
            assertThat(price).isEqualTo(priceHistory.price)
        }
    }

    @Test
    fun `Check Price histroy returns list of prices`() {
        assertThat(service.priceHistory(expectedProductId).contains(priceHistory)).isTrue()
    }

    @Test
    fun `Check Product removal`() {
        service.removeProduct(expectedProductId)
        assertThat(service.priceHistory(expectedProductId)).isEmpty()
    }
}
