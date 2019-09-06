package com.training.project.service.model

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface PriceHistoryRepository : CrudRepository<PriceHistory, UUID> {
    fun findFirst10ByProductIdOrderByTimestampDesc(productId: UUID): List<PriceHistory>
    fun deleteAllByProductId(productId: UUID)
}