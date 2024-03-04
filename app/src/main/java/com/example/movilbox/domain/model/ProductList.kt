package com.example.movilbox.domain.model

import com.example.movilbox.data.database.entities.ProductEntity
import com.example.movilbox.data.model.ProductListResponse

data class ProductList(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
)

fun ProductEntity.toDomainModel(): ProductList =
    ProductList(
        brand = brand,
        category = category,
        description = description,
        discountPercentage = discountPercentage,
        id = id,
        images = arrayListOf(),
        price = price,
        rating = rating,
        stock = stock,
        thumbnail = thumbnail,
        title = title
    )

fun ProductListResponse.toDomainModel(): ProductList =
    ProductList(
        brand = brand,
        category = category,
        description = description,
        discountPercentage = discountPercentage,
        id = id,
        images = images,
        price = price,
        rating = rating,
        stock = stock,
        thumbnail = thumbnail,
        title = title
    )
