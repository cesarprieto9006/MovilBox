package com.example.movilbox.di

import com.example.movilbox.data.datasource.ProductRemoteDataSource
import com.example.movilbox.data.datasource.ProductServerDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindRemoteDataSource(impl: ProductServerDataSource): ProductRemoteDataSource
}
