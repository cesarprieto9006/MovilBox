package com.example.movilbox.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movilbox.domain.model.ProductList

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "brand") val brand: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "images") val images: List<String>,
    @ColumnInfo(name = "discountPercentage") val discountPercentage: Double,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "rating") val rating: Double,
    @ColumnInfo(name = "stock") val stock: Int,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "title") val title: String
)

fun ProductList.toDatabase() = ProductEntity(
    id = id,
    brand = brand,
    category = category,
    description = description,
    discountPercentage = discountPercentage,
    price = price,
    rating = rating,
    stock = stock,
    thumbnail = thumbnail,
    images = images,
    title = title
)
