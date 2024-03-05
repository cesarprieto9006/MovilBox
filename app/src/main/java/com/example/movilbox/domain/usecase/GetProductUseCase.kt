package com.example.movilbox.domain.usecase

import arrow.core.Either
import com.example.movilbox.data.repository.ProductRepository
import com.example.movilbox.di.GeneralError
import com.example.movilbox.domain.model.Product
import com.example.movilbox.domain.model.ProductList
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke(): Either<GeneralError, Product> =
        repository.getProduct()

    suspend fun getAllProduct(): List<ProductList> =
        repository.getAllProductFromDatabase()

    suspend fun getProductName(name: String): List<ProductList> =
        repository.getProductName(name)

    suspend fun getProductId(id: String): ProductList =
        repository.getProductId(id)

    suspend fun getPriceDesc(): List<ProductList> =
        repository.getPriceDesc()

    suspend fun getPriceAsc(): List<ProductList> =
        repository.getPriceAsc()

    suspend fun getDiscountDesc(): List<ProductList> =
        repository.getDiscountDesc()

    suspend fun getDiscountAsc(): List<ProductList> =
        repository.getDiscountAsc()

    suspend fun getCategoryDesc(): List<ProductList> =
        repository.getCategoryDesc()

    suspend fun getCategoryAsc(): List<ProductList> =
        repository.getCategoryAsc()

    suspend fun getRatingDesc(): List<ProductList> =
        repository.getRatingDesc()

    suspend fun getRatingAsc(): List<ProductList> =
        repository.getRatingAsc()

    suspend fun getStockAsc(): List<ProductList> =
        repository.getStockAsc()

    suspend fun getStockDesc(): List<ProductList> =
        repository.getStockDesc()

    suspend fun getBrandAsc(): List<ProductList> =
        repository.getBrandAsc()

    suspend fun getBrandDesc(): List<ProductList> =
        repository.getBrandDesc()

}