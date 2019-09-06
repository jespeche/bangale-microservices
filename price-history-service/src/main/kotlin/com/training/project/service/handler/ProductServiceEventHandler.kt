package com.training.project.service.handler

import com.training.project.service.PriceHistoryService
import com.training.project.service.model.ProductDeregistered
import com.training.project.service.model.ProductPriceDecreased
import com.training.project.service.model.ProductPriceIncreased
import com.training.project.service.model.ProductPriceSet
import com.training.project.service.model.ProductRegistered
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ProductServiceEventHandler(private val service: PriceHistoryService) {

    @EventListener
    fun handleProductRegistered(event: ProductRegistered) = with(event) {
        service.registerPrice(id, price.currency, price.amount)
    }

    @EventListener
    fun handleProductPriceIncreased(event: ProductPriceIncreased) = with(event) {
        service.registerPrice(id, price.currency, price.amount)
    }

    @EventListener
    fun handleProductPriceDecreased(event: ProductPriceDecreased) = with(event) {
        service.registerPrice(id, price.currency, price.amount)
    }

    @EventListener
    fun handleProductPriceSet(event: ProductPriceSet) = with(event) {
        service.registerPrice(id, price.currency, price.amount)
    }

    @EventListener
    fun handleProductDeregistered(event: ProductDeregistered) = with(event) {
        service.removeProduct(id)
    }
}