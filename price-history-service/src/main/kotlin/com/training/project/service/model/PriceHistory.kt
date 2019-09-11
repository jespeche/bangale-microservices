package com.training.project.service.model

import com.training.project.service.model.Currency.DOLLAR
import java.util.Date
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class PriceHistory(
        var productId: UUID = UUID.randomUUID(),
        var price: Price = Price(DOLLAR, 0.0),
        var timestamp: Date = Date(),
        @Id @GeneratedValue
        val id: UUID = UUID.randomUUID()
)