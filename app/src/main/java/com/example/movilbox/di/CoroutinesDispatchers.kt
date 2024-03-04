package com.example.movilbox.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesDispatchers {

    @Provides
    @Named("Default")
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Named("IO")
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
