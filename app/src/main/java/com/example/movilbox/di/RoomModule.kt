package com.example.movilbox.di

import android.content.Context
import androidx.room.Room
import com.example.movilbox.data.database.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val MOVIL_BOX_DATABASE_NAME = "movilbox_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ProductDatabase::class.java, MOVIL_BOX_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideQuoteDao(db: ProductDatabase) = db.getProductDao()
}