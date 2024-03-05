package com.example.movilbox.data.datasource
import arrow.core.Either
import com.example.movilbox.di.GeneralError
import com.example.movilbox.domain.model.Product

interface ProductRemoteDataSource {
    suspend fun getCategory(): Either<GeneralError, List<String>>
    suspend fun getProduct(): Either<GeneralError, Product>
}