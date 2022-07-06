package com.andres.ausecha.cashier.domain

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity
data class Client(
    @Id
    val id: UUID,
    val name: String
)

@Entity
data class Order(
    @Id
    val id: UUID,
    val date: LocalDateTime,
    @OneToMany
    val products: List<Product>,
    val total: Long,
    @OneToOne
    val client: Client
)

@Entity
data class Product(
    @Id
    val id: UUID,
    val name: String,
    val price: Long
)

@Entity
data class Inventory(
    @Id
    val id: UUID,
    @OneToOne
    val product: Product,
    var quantity: Int
)
