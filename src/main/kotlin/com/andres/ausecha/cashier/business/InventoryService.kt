package com.andres.ausecha.cashier.business

import com.andres.ausecha.cashier.DTOs.ProductAndQuantity
import com.andres.ausecha.cashier.domain.Inventory
import com.andres.ausecha.cashier.domain.InventoryRepository
import org.springframework.stereotype.Service

@Service
class InventoryService(
    val inventoryRepo: InventoryRepository
) {
    @Synchronized
    fun updateProductsAvailabilityByIds(productAndQuantities: List<ProductAndQuantity>): MutableIterable<Inventory> =
        inventoryRepo.saveAll(
            inventoryRepo
                .findAllByProductIds(productAndQuantities.map { p -> p.productId })
                .map {
                    val productAndQuantity = productAndQuantities.find { paq -> paq.productId == it.id }
                        ?: throw Exception("Not found product ID to subtract")

                    if (productAndQuantity.quantity > it.quantity) {
                        throw Exception("Invalid quantity")
                    }

                    it.quantity = it.quantity - productAndQuantity.quantity

                    it
                }
        )
}
