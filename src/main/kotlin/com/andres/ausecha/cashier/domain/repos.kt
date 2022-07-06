package com.andres.ausecha.cashier.domain

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClientRepository: CrudRepository<Client, UUID>

@Repository
interface ProductRepository: CrudRepository<Product, UUID>

@Repository
interface OrderRepository: CrudRepository<Order, UUID>

@Repository
interface InventoryRepository: CrudRepository<Inventory, UUID> {
    @Query("SELECT I from Inventory I WHERE I.id IN :productIds")
    fun findAllByProductIds(productIds: List<UUID>): List<Inventory>
}