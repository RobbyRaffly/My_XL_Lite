package com.myxllite.app.core.network

import android.content.Context
import com.google.gson.Gson
import com.myxllite.app.BuildConfig
import com.myxllite.app.core.localdatasource.cache.AppPreferences
import com.myxllite.app.core.network.util.ForceTls12Util
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
object NetworkProvider {
    private const val CONNECT_TIMEOUT_IN_SECOND: Long = 100
    private const val HEADER_CONTENT_TYPE = "Content-Type"
    private const val HEADER_CONTENT_TYPE_VALUE = "application/json"

    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null
    private var gsonConverterFactory: GsonConverterFactory? = null
    private var gson: Gson? = null

    @JvmStatic
    fun getRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        if (retrofit == null) {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_API_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
        }
        return retrofit as Retrofit
    }

    @JvmStatic
    private fun getRequestInterceptor(
        context: Context,
        appPreferences: AppPreferences
    ): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .header(HEADER_CONTENT_TYPE, HEADER_CONTENT_TYPE_VALUE)

            // Request customization: add query param
            val modifiedUrl = original.url.newBuilder()
                .build()

            val request = requestBuilder.url(modifiedUrl).build()

            val response = chain.proceed(request)

            return@Interceptor response
        }
    }

    @JvmStatic
    fun getOkHttpClient(context: Context, appPreferences: AppPreferences): OkHttpClient {
        if (okHttpClient == null) {
            val builder = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
                .readTimeout(CONNECT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
                .writeTimeout(CONNECT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
                .addInterceptor(getRequestInterceptor(context, appPreferences))

            okHttpClient = ForceTls12Util.enableTls12OnPreLollipop(builder)
                .build()
        }

        return okHttpClient as OkHttpClient
    }

    @JvmStatic
    fun getGsonConverterFactory(gson: Gson): GsonConverterFactory {
        if (gsonConverterFactory == null) {
            gsonConverterFactory = GsonConverterFactory.create(gson)
        }

        return gsonConverterFactory as GsonConverterFactory
    }

    @JvmStatic
    fun getGson(): Gson {
        if (gson == null) {
            gson = Gson()
        }
        return gson as Gson
    }

    @JvmStatic
    fun getGlideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        return ForceTls12Util.enableTls12OnPreLollipop(builder).build()
    }
}