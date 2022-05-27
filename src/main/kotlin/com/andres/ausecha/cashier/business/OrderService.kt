package com.andres.ausecha.cashier.business

import com.andres.ausecha.cashier.domain.*
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDateTime.now
import java.util.*

// This service method save acts like a facade for the whole operation, it delegates other steps to the rest of services

class OrderService (
    val clientRepository: ClientRepository,
    val productService: ProductService,
    val orderRepository: orderRepository,
    val inventoryService: InventoryService
): IOrderFacade {
    @Synchronized
    override fun save(clientId: UUID, productAndQuantities: List<ProductAndQuantity>): Order {
        val registeredClient = clientRepository.findByIdOrNull(clientId)
            ?: throw Exception("client not found")
        val products: List<Product> = productService.findByIds(productAndQuantities.map{ p -> p.productId })
        val order = Order(
            id = UUID.randomUUID(),
            date = now(),
            products = products,
            total = productService.calculatePrice(products),
            client = registeredClient
        )

        val savedOrder = orderRepository.save(order)
        inventoryService.updateProductsAvailabilityByIds(productAndQuantities)
        return savedOrder
    }
}

data class ProductAndQuantity(
    val productId: UUID,
    val quantity: Int
)