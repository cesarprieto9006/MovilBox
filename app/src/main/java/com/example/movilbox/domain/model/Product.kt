package com.example.movilbox.domain.model

import com.google.gson.annotations.SerializedName

data class Product(
    val limit: Int,
    val products: List<ProductList>,
    val skip: Int,
    val total: Int
)