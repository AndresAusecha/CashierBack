package com.andres.ausecha.cashier.DTOs


import java.util.*

data class OrderRequest(
    val clientId: UUID,
    val productAndQuantities: List<ProductAndQuantity>
)