package com.training.project.service

import com.training.project.service.model.Currency
import com.training.project.service.model.PriceHistory
import java.util.UUID

interface PriceHistoryService {
    fun priceHistory(productId: UUID): List<PriceHistory>
    fun registerPrice(productId: UUID, currency: Currency, price: Double): PriceHistory
    fun removeProduct(productId: UUID)
}