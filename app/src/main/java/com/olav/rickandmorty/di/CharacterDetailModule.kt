package com.olav.rickandmorty.di

import android.app.appsearch.StorageInfo
import com.olav.rickandmorty.CharacterDetailActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object CharacterDetailModule {

    @SecondRandomString
    @Provides
    fun provideAnotherRandomString(): String {
        return "This is a random string..."
    }
}

@Qualifier
annotation class SecondRandomString