package com.pragma.entomologo.di.ui

import com.pragma.entomologo.ui.dispatchers.DispatcherProvider
import com.pragma.entomologo.ui.dispatchers.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = DispatcherProviderImpl()
}