package com.andres.ausecha.cashier.DTOs

import com.andres.ausecha.cashier.business.ProductAndQuantity
import java.util.*

data class OrderRequest(
    val clientId: UUID,
    val productAndQuantities: List<ProductAndQuantity>
)