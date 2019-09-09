package com.training.project.messaging

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.training.project.messaging.subscriptions.ProductsServiceListener
import com.training.project.service.model.Currency
import com.training.project.service.model.Price
import com.training.project.service.model.ProductEvent
import com.training.project.service.model.ProductRegistered
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationEventPublisher
import java.util.UUID
import java.util.UUID.randomUUID

/**
 * Product Service Listener Unit Test.
 *
 * It checks the interactions between listener function and a mocked service
 * to be sure that the delegation is correct.
 */
class ProductServiceListenerTests {


    private lateinit var publisher: ApplicationEventPublisher
    private lateinit var listner: ProductsServiceListener
    val productId = randomUUID()

    @BeforeEach
    fun setUp() {
        publisher = mock()
        listner = ProductsServiceListener(publisher)
    }


    @Test
    fun `Check handle publishes appropriate event`() {
        val emptyString = ""
        val event = ProductRegistered(productId, emptyString, Price(Currency.DOLLAR, 10.0))
        listner.handle(event)

        val eventCaptor = argumentCaptor<ProductEvent>()
        verify(publisher).publishEvent(eventCaptor.capture())

        val expected = ProductRegistered(productId, emptyString, Price(Currency.DOLLAR, 10.0))
        assertThat(eventCaptor.lastValue).isEqualTo(expected)
    }

}