package com.example.movilbox.data.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.movilbox.data.database.dao.ProductDao
import com.example.movilbox.data.database.entities.ProductEntity
import com.example.movilbox.data.database.entities.toDatabase
import com.example.movilbox.data.datasource.ProductRemoteDataSource
import com.example.movilbox.di.GeneralError
import com.example.movilbox.domain.model.Product
import com.example.movilbox.domain.model.ProductList
import com.example.movilbox.domain.model.toDomainModel
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource,
    private val productDao: ProductDao
) {

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
                { products ->
                    deleteProduct()
                    insertProduct(products.products.map { it.toDatabase() })
                    products.right()
                }
            )
    }

    private suspend fun insertProduct(productList: List<ProductEntity>) {
        productDao.insertAll(productList)
    }

    private suspend fun deleteProduct() {
        productDao.deleteProducts()
    }

    suspend fun getProductName(name: String): List<ProductList> {
        val response: List<ProductEntity> = productDao.getProductName(name)
        return response.map {
            it.toDomainModel()
        }
    }

    suspend fun getAllProductFromDatabase(): List<ProductList> {
        val response: List<ProductEntity> = productDao.getAllProduct()
        return response.map { it.toDomainModel() }
    }

    suspend fun getProductId(id:String): ProductList {
        val response: ProductEntity = productDao.getProductId(id)
        return response.toDomainModel()
    }
}