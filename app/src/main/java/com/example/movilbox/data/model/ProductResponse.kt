package com.example.movilbox.data.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("products")
    val products: List<ProductListResponse>,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("total")
    val total: Int
)