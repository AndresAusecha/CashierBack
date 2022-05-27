package com.andres.ausecha.cashier.business

import com.andres.ausecha.cashier.domain.Order
import java.util.*

interface IOrderFacade {
    fun save(clientId: UUID, productAndQuantities: List<ProductAndQuantity>): Order
}