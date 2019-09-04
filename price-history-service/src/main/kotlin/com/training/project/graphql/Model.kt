package com.training.project.graphql

import com.training.project.service.model.Price as PriceModel

data class Price(private val price: PriceModel) {
    val currency = Currency.valueOf(price.currency.name)
    val amount: Float = price.amount.toFloat()
}

enum class Currency { DOLLAR, PESOS, RUPEES }
