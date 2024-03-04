package com.example.movilbox.data.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.movilbox.data.datasource.ProductRemoteDataSource
import com.example.movilbox.di.GeneralError
import com.example.movilbox.domain.model.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(private val remoteDataSource: ProductRemoteDataSource) {
    suspend fun getCategories(): Either<GeneralError, List<String>> {
        return remoteDataSource.getCategory()
            .fold(
                { error -> error.left() },
                { categories -> categories.right() }
            )
    }

    suspend fun getProduct(): Either<GeneralError, Product> {
        return remoteDataSource.getProduct()
            .fold(
                { error -> error.left() },
                { products -> products.right() }
            )
    }
}