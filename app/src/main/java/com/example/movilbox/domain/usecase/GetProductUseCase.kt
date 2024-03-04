package com.example.movilbox.domain.usecase

import arrow.core.Either
import com.example.movilbox.data.repository.ProductRepository
import com.example.movilbox.di.GeneralError
import com.example.movilbox.domain.model.Product
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val repository: ProductRepository) {
    /*suspend operator fun invoke(): Either<GeneralError, List<String>> =
        repository.getCategories()*/

    suspend operator fun invoke(): Either<GeneralError, Product> =
        repository.getProduct()
}