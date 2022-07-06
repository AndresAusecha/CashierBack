package com.andres.ausecha.cashier.business

import com.andres.ausecha.cashier.DTOs.ProductAndQuantity
import com.andres.ausecha.cashier.domain.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime.now
import java.util.*

// This service method save acts like a facade for the whole operation, it delegates other steps to the rest of services

@Service
class OrderService (
    val clientRepository: ClientRepository,
    val productService: ProductService,
    val orderRepository: OrderRepository,
    val inventoryService: InventoryService
): IOrderFacade {
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