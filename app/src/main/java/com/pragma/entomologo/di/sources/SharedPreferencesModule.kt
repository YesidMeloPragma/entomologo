package com.pragma.entomologo.di.sources

import android.content.Context
import com.pragma.entomologo.sources.sharedPreferences.CustomSharedPreferences
import com.pragma.entomologo.sources.sharedPreferences.CustomSharedPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context) : CustomSharedPreferences {
        CustomSharedPreferencesImpl.initInstance(context = context)
        return CustomSharedPreferencesImpl.getInstance()
    }
}