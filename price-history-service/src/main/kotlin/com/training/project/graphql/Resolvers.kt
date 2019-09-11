package com.training.project.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.training.project.service.PriceHistoryService
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ProductQueryResolver(private val service: PriceHistoryService) : GraphQLQueryResolver {
    fun priceHistory(productId: UUID) = service.priceHistory(productId).map { PriceHistory(it) }
}