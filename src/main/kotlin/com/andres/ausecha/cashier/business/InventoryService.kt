package com.andres.ausecha.cashier.business

import com.andres.ausecha.cashier.domain.InventoryRepository
import com.andres.ausecha.cashier.domain.Product
import com.andres.ausecha.cashier.domain.inventoryRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InventoryService(
    val inventoryRepository: InventoryRepository,
) {
    fun updateProductsAvailabilityByIds(productAndQuantities: List<ProductAndQuantity>) =
        inventoryRepository.saveAll(
            inventoryRepository
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
