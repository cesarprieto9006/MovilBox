package com.example.movilbox.data.datasource

import arrow.core.Either
import com.example.movilbox.data.network.ProductApiClient
import com.example.movilbox.di.GeneralError
import com.example.movilbox.domain.model.Product
import com.example.movilbox.domain.model.toDomainModel
import com.example.movilbox.extension.tryCall
import javax.inject.Inject

class ProductServerDataSource @Inject constructor(
    private val productApiClient: ProductApiClient
) : ProductRemoteDataSource {


    override suspend fun getCategory(): Either<GeneralError, List<String>> = tryCall {
        productApiClient.getAllCategories()
    }

    override suspend fun getProduct(): Either<GeneralError, Product> = tryCall {
        productApiClient.getAllProducts().toDomainModel()
    }
}
