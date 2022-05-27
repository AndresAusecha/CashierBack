package com.andres.ausecha.cashier.network

import com.andres.ausecha.cashier.DTOs.OrderRequest
import com.andres.ausecha.cashier.business.OrderService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController (
    val orderService: OrderService
){
    @RequestMapping(method = [RequestMethod.POST])
    fun create(@RequestBody req: OrderRequest) = orderService.save(req.clientId, req.productAndQuantities)
}

