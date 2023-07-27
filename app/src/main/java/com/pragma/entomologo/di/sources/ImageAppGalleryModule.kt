package com.pragma.entomologo.di.sources

import android.content.Context
import android.os.Environment
import com.pragma.entomologo.sources.appImageGallery.ImageAppGallery
import com.pragma.entomologo.sources.appImageGallery.ImageAppGalleryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ImageAppGalleryModule {

    @Provides
    fun providesImageAppGallery(
        @ApplicationContext context: Context
    ) : ImageAppGallery = ImageAppGalleryImpl(
        basePath = "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)}",
        context = context
    )
}