package com.example.movilbox.domain.model

import com.example.movilbox.data.model.ProductResponse

data class Product(
    val limit: Int, val products: List<ProductList>, val skip: Int, val total: Int
)

fun ProductResponse.toDomainModel(): Product = Product(
    limit = limit, products = products.map { it.toDomainModel() }, skip = skip, total = total
)