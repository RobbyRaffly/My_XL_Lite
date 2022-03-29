package com.myxllite.app.core.di

import android.content.Context
import com.google.gson.Gson
import com.myxllite.app.core.localdatasource.cache.AppPreferences
import com.myxllite.app.core.localdatasource.roomdb.AppDatabase
import com.myxllite.app.core.scheduler.SchedulerProvider
import com.myxllite.app.domain.repository.local.LocalDataSource
import com.myxllite.app.domain.repository.remote.RemoteDataSource
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidContextModule::class, CoreModule::class])
interface CoreComponent {

    fun context(): Context

    fun gson(): Gson

    fun okHttpClient(): OkHttpClient

    fun retrofit(): Retrofit

    fun schedulerProvider(): SchedulerProvider

    fun appDatabase(): AppDatabase

    fun appPreferences(): AppPreferences

    fun remoteDataSource(): RemoteDataSource

    fun localDataSource(): LocalDataSource
}
