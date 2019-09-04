/*
package com.training.project.service

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.training.project.service.implementation.PriceHistoryServiceImpl
import com.training.project.service.model.Currency.PESOS
import com.training.project.service.model.Price
import com.training.project.service.model.PriceHistory
import com.training.project.service.model.PriceHistoryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.AdditionalAnswers.returnsFirstArg
import org.springframework.context.ApplicationEventPublisher
import java.util.Optional
import java.util.UUID

class PriceHistoryServiceTests {

    private lateinit var service: PriceHistoryService

    private var repository: PriceHistoryRepository = mock()
    private var publisher: ApplicationEventPublisher = mock()
    //private val expectedProduct = PriceHistory("Robot", Price(PESOS, 100.0))

    @BeforeEach
    fun setUp() {
        service = PriceHistoryServiceImpl(repository, publisher)
    }

    @Test
    fun `Check Product is retrieved`() {
        whenever(repository.findById(any())).thenReturn(Optional.of(expectedProduct))
        val product = service.product(expectedProduct.id)

        assertThat(product).isNotNull
        assertThat(product).isEqualTo(expectedProduct)
    }

    @Test
    fun `Check Product is retrieved failure throws exception`() {
        whenever(repository.findById(any())).thenThrow(NoSuchElementException())
        assertThrows<NoSuchElementException> { service.product(UUID.randomUUID()) }
    }

    @Test
    fun `Check Products are retrieved in a list`() {
        whenever(repository.findAll()).thenReturn(listOf(expectedProduct))
        val products = service.products()

        assertThat(products).isNotEmpty
        assertThat(products).contains(expectedProduct)
    }

    @Test
    fun `Check Product registration`() {
        whenever(repository.save(any<Product>())).then(returnsFirstArg<Product>())
        val product = service.registerProduct("Robot", PESOS, 100.0)

        product.apply {
            assertThat(name).isEqualTo(expectedProduct.name)
            assertThat(price).isEqualTo(expectedProduct.price)
        }
    }

}*/
