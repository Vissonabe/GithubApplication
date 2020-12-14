package com.viswa.network.di

import android.content.Context
import com.viswa.network.db.RepoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideRepoDatabase(@ApplicationContext context: Context) : RepoDatabase {
        return RepoDatabase.getInstance(context)
    }
}