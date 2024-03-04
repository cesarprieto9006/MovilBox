package com.example.movilbox.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movilbox.data.database.dao.ProductDao
import com.example.movilbox.data.database.entities.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun getProductDao(): ProductDao
}