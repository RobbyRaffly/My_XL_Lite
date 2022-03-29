package com.myxllite.app.core.di

import android.content.Context
import com.google.gson.Gson
import com.myxllite.app.core.localdatasource.LocalDataSourceProvider
import com.myxllite.app.core.localdatasource.cache.AppPreferences
import com.myxllite.app.core.localdatasource.roomdb.AppDatabase
import com.myxllite.app.core.network.NetworkProvider
import com.myxllite.app.core.scheduler.AppSchedulerProvider
import com.myxllite.app.core.scheduler.SchedulerProvider
import com.myxllite.app.data.ApiService
import com.myxllite.app.domain.repository.local.LocalDataSource
import com.myxllite.app.domain.repository.local.LocalRepository
import com.myxllite.app.domain.repository.remote.RemoteDataSource
import com.myxllite.app.domain.repository.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object CoreModule {

    @JvmStatic
    @Provides
    fun provideGson(): Gson {
        return NetworkProvider.getGson()
    }

    @JvmStatic
    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return NetworkProvider.getGsonConverterFactory(gson)
    }

    @JvmStatic
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @JvmStatic
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return LocalDataSourceProvider.getAppDatabase(context)
    }

    @JvmStatic
    @Provides
    fun provideAppPreferences(context: Context): AppPreferences {
        return LocalDataSourceProvider.getAppPreferences(context)
    }

    @JvmStatic
    @Provides
    fun provideOkHttpClient(context: Context, appPreferences: AppPreferences): OkHttpClient {
        return NetworkProvider.getOkHttpClient(context, appPreferences)
    }

    @JvmStatic
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return NetworkProvider.getRetrofit(okHttpClient, gsonConverterFactory)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService, gson: Gson): RemoteDataSource {
        return RemoteRepository(apiService, gson)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideLocalDataSource(
        appPreferences: AppPreferences,
        appDatabase: AppDatabase,
        gson: Gson
    ): LocalDataSource {
        return LocalRepository(appPreferences, appDatabase, gson)
    }
}
