package com.training.project.messaging.subscriptions

import com.training.project.service.model.ProductEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@KafkaListener(id = "price-history-service", topics = ["products-service"])
internal class ProductsServiceListener(private val publisher: ApplicationEventPublisher) {

    @KafkaHandler
    fun handle(event: ProductEvent) {
        println("ProductEvent Received -> $event")
        publisher.publishEvent(event)
    }
}
