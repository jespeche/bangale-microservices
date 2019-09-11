package com.training.project.graphql

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.training.project.service.PriceHistoryService
import com.training.project.service.model.Currency
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Date
import java.util.NoSuchElementException
import java.util.UUID.randomUUID
import com.training.project.service.model.Price as PriceModel
import com.training.project.service.model.PriceHistory as PriceHistoryModel

class QueryResolverTests {

    private lateinit var service: PriceHistoryService
    private lateinit var resolver: ProductQueryResolver

    private val priceModel = PriceModel(Currency.DOLLAR, 10.0)
    private val productId = randomUUID()
    private val priceHistoryModel = PriceHistoryModel(productId, priceModel, Date(), productId)

    @BeforeEach
    fun setUp() {
        service = mock()
        resolver = ProductQueryResolver(service)
    }

    @Test
    fun `Check Product retrieval`() {
        whenever(service.priceHistory(any())).thenReturn(listOf(priceHistoryModel))
        resolver.priceHistory(productId).apply {
            assertThat(productId).isEqualTo(priceHistoryModel.id)
        }
    }

    @Test
    fun `Check Price History retrieval throws exception when its empty`() {
        whenever(service.priceHistory(any())).thenThrow(NoSuchElementException())
        assertThrows<NoSuchElementException> { resolver.priceHistory(randomUUID()) }
    }

    @Test
    fun `Check Price Histrory retrieval is a list`() {
        whenever(service.priceHistory(any())).thenReturn(listOf(priceHistoryModel))
        resolver.priceHistory(productId).apply {
            assertThat(this).isNotEmpty
        }
    }
}
