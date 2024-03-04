package com.example.movilbox.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movilbox.data.database.entities.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table ORDER BY rating DESC")
    suspend fun getAllProduct(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes: List<ProductEntity>)

    @Query(
        "SELECT * FROM product_table WHERE title LIKE  '%' || :title || '%' ORDER BY rating DESC")
    suspend fun getProductName(title: String): List<ProductEntity>

    @Query("SELECT * FROM product_table WHERE id = :idProduct")
    suspend fun getProductId(idProduct:String): ProductEntity

    @Query("DELETE FROM product_table")
    suspend fun deleteProducts()
}