package com.andres.ausecha.cashier.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClientRepository: JpaRepository<Client, UUID>

@Repository
interface ProductRepository: JpaRepository<Product, UUID>

@Repository
interface OrderRepository: JpaRepository<Order, UUID>

@Repository
interface InventoryRepository: JpaRepository<Inventory, UUID> {
    @Query("SELECT I from Inventory I WHERE I.id IN :productIds")
    fun findAllByProductIds(productIds: List<UUID>): List<Inventory>
}