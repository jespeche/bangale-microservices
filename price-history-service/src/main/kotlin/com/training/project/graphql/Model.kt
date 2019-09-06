package com.training.project.graphql

import com.training.project.service.model.Price as PriceModel
import com.training.project.service.model.PriceHistory as PriceHistoryModel

data class Price(private val price: PriceModel) {
    val currency = Currency.valueOf(price.currency.name)
    val amount: Float = price.amount.toFloat()
}

data class PriceHistory(private val priceHistory: PriceHistoryModel) {
    val productId = priceHistory.productId
    val price: Price = Price(priceHistory.price)
    val timestamp = priceHistory.timestamp.toString()
}

enum class Currency { DOLLAR, PESOS, RUPEES }
