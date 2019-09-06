package com.training.project.service.model

import java.util.UUID
import java.util.UUID.randomUUID

interface ProductEvent

data class ProductRegistered(val id: UUID = randomUUID(), val name: String = "", val price: Price = Price(Currency.DOLLAR, 0.0)) : ProductEvent
data class ProductDeregistered(val id: UUID = randomUUID()) : ProductEvent
data class ProductPriceIncreased(val id: UUID = randomUUID(), val price: Price = Price(Currency.DOLLAR, 0.0), val oldPrice: Price = Price(Currency.DOLLAR, 0.0)) : ProductEvent
data class ProductPriceDecreased(val id: UUID = randomUUID(), val price: Price = Price(Currency.DOLLAR, 0.0), val oldPrice: Price = Price(Currency.DOLLAR, 0.0)) : ProductEvent
data class ProductPriceSet(val id: UUID = randomUUID(), val price: Price = Price(Currency.DOLLAR, 0.0), val oldPrice: Price = Price(Currency.DOLLAR, 0.0)) : ProductEvent
