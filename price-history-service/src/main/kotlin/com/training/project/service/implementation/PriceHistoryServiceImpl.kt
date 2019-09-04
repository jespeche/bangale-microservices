package com.training.project.service.implementation

import com.training.project.service.PriceHistoryService
import com.training.project.service.model.Currency
import com.training.project.service.model.Price
import com.training.project.service.model.PriceHistory
import com.training.project.service.model.PriceHistoryRepository
import org.springframework.stereotype.Service
import java.util.UUID
import javax.transaction.Transactional

@Service
@Transactional
class PriceHistoryServiceImpl(private val repository: PriceHistoryRepository) : PriceHistoryService {
    override fun priceHistory(productId: UUID) = repository.findFirst10ByProductIdOrderByTimestampDesc(productId)
    override fun registerPrice(productId: UUID, currency: Currency, price: Double) = repository.save(PriceHistory(productId, Price(currency, price)))
}