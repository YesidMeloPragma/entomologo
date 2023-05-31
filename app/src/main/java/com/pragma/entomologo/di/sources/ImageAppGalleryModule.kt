package com.pragma.entomologo.di.sources

import com.pragma.entomologo.sources.appImageGallery.ImageAppGallery
import com.pragma.entomologo.sources.appImageGallery.ImageAppGalleryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ImageAppGalleryModule {

    @Provides
    fun providesImageAppGallery() : ImageAppGallery = ImageAppGalleryImpl()
}