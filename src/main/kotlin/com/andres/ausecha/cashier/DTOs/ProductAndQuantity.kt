package com.andres.ausecha.cashier.DTOs

import java.util.*

data class ProductAndQuantity(
    val productId: UUID,
    val quantity: Int
)