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
        "SELECT * FROM product_table WHERE title LIKE  '%' || :title || '%' ORDER BY rating DESC"
    )
    suspend fun getProductName(title: String): List<ProductEntity>

    @Query("SELECT * FROM product_table WHERE id = :idProduct")
    suspend fun getProductId(idProduct: String): ProductEntity

    @Query("DELETE FROM product_table")
    suspend fun deleteProducts()

    @Query("SELECT * FROM product_table ORDER BY price DESC")
    suspend fun getPriceDesc(): List<ProductEntity>

    @Query("SELECT * FROM product_table ORDER BY price ASC")
    suspend fun getPriceAsc(): List<ProductEntity>

    @Query("SELECT * FROM product_table ORDER BY discountPercentage DESC")
    suspend fun getDiscountDesc(): List<ProductEntity>

    @Query("SELECT * FROM product_table ORDER BY discountPercentage ASC")
    suspend fun getDiscountAsc(): List<ProductEntity>

    @Query("SELECT * FROM product_table ORDER BY category DESC")
    suspend fun getCategoryDesc(): List<ProductEntity>

    @Query("SELECT * FROM product_table ORDER BY category ASC")
    suspend fun getCategoryAsc(): List<ProductEntity>

    @Query("SELECT * FROM product_table ORDER BY rating ASC")
    suspend fun getRatingAsc(): List<ProductEntity>

    @Query("SELECT * FROM product_table ORDER BY rating Desc")
    suspend fun getRatingDesc(): List<ProductEntity>

    @Query("SELECT * FROM product_table ORDER BY stock ASC")
    suspend fun getStockAsc(): List<ProductEntity>

    @Query("SELECT * FROM product_table ORDER BY stock Desc")
    suspend fun getStockDesc(): List<ProductEntity>

    @Query("SELECT * FROM product_table ORDER BY brand ASC")
    suspend fun getBrandAsc(): List<ProductEntity>

    @Query("SELECT * FROM product_table ORDER BY brand Desc")
    suspend fun getBrandDesc(): List<ProductEntity>
}