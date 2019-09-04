package com.training.project.service.model

import javax.persistence.Embeddable

@Embeddable
data class Price(val currency: Currency, val amount: Double)

enum class Currency { DOLLAR, PESOS, RUPEES }
