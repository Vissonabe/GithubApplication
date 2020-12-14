package com.viswa.stockapplication.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.viswa.network.service.IGithubService
import com.viswa.stockapplication.data.GithubRepository
import com.viswa.network.db.RepoDatabase
import com.viswa.stockapplication.splash.BioMetricAuthImpl
import com.viswa.stockapplication.splash.IBiometricAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object FragmentModule {
    @Provides
    @Singleton
    fun provideGithubRepo(service : IGithubService, repoDb : RepoDatabase) : GithubRepository {
        return GithubRepository(service, repoDb)
    }
}

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {
    @Provides
    @ActivityScoped
    fun provideBiometricAuthImpl(@ActivityContext context: Context) : IBiometricAuth {
        return BioMetricAuthImpl(context)
    }
}