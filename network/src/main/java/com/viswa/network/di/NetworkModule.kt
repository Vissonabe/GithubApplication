package com.viswa.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.viswa.network.service.IGithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides @Singleton
    fun provideChuck(@ApplicationContext context: Context) : ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }

    @Provides @Singleton
    fun provideRetrofit(chuck : ChuckerInterceptor) : Retrofit {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC

        val client = OkHttpClient.Builder()
            .addInterceptor(chuck)
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides @Singleton
    fun provideGithubService(retrofit : Retrofit) : IGithubService {
        return retrofit.create(IGithubService::class.java)
    }
}