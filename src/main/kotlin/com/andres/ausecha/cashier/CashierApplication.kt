package com.andres.ausecha.cashier

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories
@SpringBootApplication
class CashierApplication

fun main(args: Array<String>) {
	runApplication<CashierApplication>(*args)
}
