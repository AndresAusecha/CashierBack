package com.andres.ausecha.cashier.business

import com.andres.ausecha.cashier.domain.Product
import com.andres.ausecha.cashier.domain.productRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProductService(
    val productRepository: productRepository
) {
    fun calculatePrice(products: List<Product>): Long = products
        .map { it.price }
        .reduce { price1, price2 -> price1 + price2 }

    fun findByIds(ids: List<UUID>) = productRepository.findAllById(ids).let {
        it.forEach { product ->
            product ?: throw Exception("Product not found")
        }

        it.toList()
    }
}