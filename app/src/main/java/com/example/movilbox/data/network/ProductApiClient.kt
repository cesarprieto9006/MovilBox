package com.example.movilbox.data.network

import com.example.movilbox.data.model.CategoryListResponse
import com.example.movilbox.data.model.ProductResponse
import retrofit2.http.GET

interface ProductApiClient {
    @GET("products/categories")
    suspend fun getAllCategories(): CategoryListResponse

    @GET("products")
    suspend fun getAllProducts(): ProductResponse
}