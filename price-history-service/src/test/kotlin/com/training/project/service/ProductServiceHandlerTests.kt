package com.training.project.service

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.training.project.service.handler.ProductServiceEventHandler
import com.training.project.service.model.Currency
import com.training.project.service.model.Currency.DOLLAR
import com.training.project.service.model.Price
import com.training.project.service.model.ProductDeregistered
import com.training.project.service.model.ProductPriceDecreased
import com.training.project.service.model.ProductPriceIncreased
import com.training.project.service.model.ProductPriceSet
import com.training.project.service.model.ProductRegistered
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.UUID
import java.util.UUID.randomUUID

/**
 * Product Service Handler Unit Test.
 *
 * It checks the interactions between each handler and a mocked service
 * to be sure that the delegation is correct.
 */
class ProductServiceHandlerTests {

    private lateinit var service: PriceHistoryService
    private lateinit var handler: ProductServiceEventHandler

    val productId = randomUUID()

    @BeforeEach
    fun setUp() {
        service = mock()
        handler = ProductServiceEventHandler(service)
    }

    @Test
    fun `Check handleProductRegistered interaction with the service`() {
        val emptyString = ""
        val event = ProductRegistered(productId, emptyString, Price(DOLLAR, 10.0))
        handler.handleProductRegistered(event)

        val idCaptor = argumentCaptor<UUID>()
        val currencyCaptor = argumentCaptor<Currency>()
        val amountCaptor = argumentCaptor<Double>()
        verify(service).registerPrice(idCaptor.capture(), currencyCaptor.capture(), amountCaptor.capture())

        val capturedPrice = Price(currencyCaptor.lastValue, amountCaptor.lastValue)
        val capturedProduct = ProductRegistered(idCaptor.lastValue, emptyString, capturedPrice)
        assertThat(event).isEqualTo(capturedProduct)
    }

    @Test
    fun `Check handleProductPriceIncreased interaction with the service`() {
        val event = ProductPriceIncreased(productId, Price(DOLLAR, 10.0))
        handler.handleProductPriceIncreased(event)

        val idCaptor = argumentCaptor<UUID>()
        val currencyCaptor = argumentCaptor<Currency>()
        val amountCaptor = argumentCaptor<Double>()
        verify(service).registerPrice(idCaptor.capture(), currencyCaptor.capture(), amountCaptor.capture())

        val capturedPrice = Price(currencyCaptor.lastValue, amountCaptor.lastValue)
        val capturedProduct = ProductPriceIncreased(idCaptor.lastValue, capturedPrice)
        assertThat(event).isEqualTo(capturedProduct)
    }

    @Test
    fun `Check handleProductPriceDecreased interaction with the service`() {
        val event = ProductPriceDecreased(productId, Price(DOLLAR, 10.0))
        handler.handleProductPriceDecreased(event)

        val idCaptor = argumentCaptor<UUID>()
        val currencyCaptor = argumentCaptor<Currency>()
        val amountCaptor = argumentCaptor<Double>()
        verify(service).registerPrice(idCaptor.capture(), currencyCaptor.capture(), amountCaptor.capture())

        val capturedPrice = Price(currencyCaptor.lastValue, amountCaptor.lastValue)
        val capturedProduct = ProductPriceDecreased(idCaptor.lastValue, capturedPrice)
        assertThat(event).isEqualTo(capturedProduct)
    }

    @Test
    fun `Check handleProductPriceSet interaction with the service`() {
        val event = ProductPriceSet(productId, Price(DOLLAR, 10.0))
        handler.handleProductPriceSet(event)

        val idCaptor = argumentCaptor<UUID>()
        val currencyCaptor = argumentCaptor<Currency>()
        val amountCaptor = argumentCaptor<Double>()
        verify(service).registerPrice(idCaptor.capture(), currencyCaptor.capture(), amountCaptor.capture())

        val capturedPrice = Price(currencyCaptor.lastValue, amountCaptor.lastValue)
        val capturedProduct = ProductPriceSet(idCaptor.lastValue, capturedPrice)
        assertThat(event).isEqualTo(capturedProduct)
    }

    @Test
    fun `Check handleProductDeRegistered interaction with the service`() {
        val event = ProductDeregistered(productId)
        handler.handleProductDeregistered(event)

        val idCaptor = argumentCaptor<UUID>()
        verify(service).removeProduct(idCaptor.capture())

        val capturedProduct = ProductDeregistered(idCaptor.lastValue)
        assertThat(event).isEqualTo(capturedProduct)
    }

}