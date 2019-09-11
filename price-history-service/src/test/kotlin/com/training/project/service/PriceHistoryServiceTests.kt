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
import java.util.Date
import java.util.UUID.randomUUID

class PriceHistoryServiceTests {

    private lateinit var service: PriceHistoryService

    private var repository: PriceHistoryRepository = mock()
    private val productId = randomUUID()
    private val priceHistoryId = randomUUID()
    private val expectedPriceHistory = PriceHistory(productId, Price(PESOS, 500.0), Date(), priceHistoryId)

    @BeforeEach
    fun setUp() {
        service = PriceHistoryServiceImpl(repository)
    }

    @Test
    fun `Check Price History is retrieved`() {
        whenever(repository.findFirst10ByProductIdOrderByTimestampDesc(any())).thenReturn(listOf(expectedPriceHistory))
        val priceHistory = service.priceHistory(productId).first()
        assertThat(priceHistory).isEqualTo(expectedPriceHistory)
    }

    @Test
    fun `Check Price History is retrieved failure throws exception`() {
        whenever(repository.findFirst10ByProductIdOrderByTimestampDesc(any())).thenThrow(NoSuchElementException())
        assertThrows<NoSuchElementException> { service.priceHistory(randomUUID()) }
    }

    @Test
    fun `Check Price History is retrieved in a list`() {
        whenever(repository.findFirst10ByProductIdOrderByTimestampDesc(any())).thenReturn(listOf(expectedPriceHistory))
        val priceHistory = service.priceHistory(productId)
        assertThat(priceHistory).isNotEmpty
        assertThat(priceHistory).contains(expectedPriceHistory)
    }

    @Test
    fun `Check Price History registration`() {
        whenever(repository.save(any<PriceHistory>())).then(returnsFirstArg<PriceHistory>())
        service.registerPrice(productId, PESOS, 500.0).apply {
            assertThat(productId).isEqualTo(expectedPriceHistory.productId)
            assertThat(price).isEqualTo(expectedPriceHistory.price)
        }
    }
}
