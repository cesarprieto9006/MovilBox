package com.example.movilbox.data.datasource

import arrow.core.Either
import com.example.movilbox.data.model.ProductListResponse
import com.example.movilbox.data.model.ProductResponse
import com.example.movilbox.data.network.ProductApiClient
import com.example.movilbox.di.GeneralError
import com.example.movilbox.domain.model.Product
import com.example.movilbox.domain.model.ProductList
import com.example.movilbox.extension.tryCall
import javax.inject.Inject

const val EMPTY_STRING = ""

class ProductServerDataSource @Inject constructor(
    private val productApiClient: ProductApiClient
) : ProductRemoteDataSource {


    override suspend fun getCategory(): Either<GeneralError, List<String>> = tryCall {
        productApiClient.getAllCategories()
    }

    override suspend fun getProduct(): Either<GeneralError, Product> = tryCall {
        productApiClient.getAllProducts().toDomainModel()
    }

    private fun ProductResponse.toDomainModel(): Product =
        Product(
            limit = limit,
            products = products.map { it.toDomainModel() },
            skip = skip,
            total = total
        )

    private fun ProductListResponse.toDomainModel(): ProductList =
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


}