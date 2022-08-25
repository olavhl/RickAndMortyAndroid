package com.olav.rickandmorty.di

import android.content.Context
import com.olav.rickandmorty.RickAndMortyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): RickAndMortyApplication {
        return app as RickAndMortyApplication
    }

    @Singleton
    @Provides
    fun provideRandomString(): String {
        return "This is a random string..."
    }
}