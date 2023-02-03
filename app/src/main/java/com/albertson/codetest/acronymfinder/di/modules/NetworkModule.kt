package com.albertson.codetest.acronymfinder.di.modules

import com.albertson.codetest.acronymfinder.BuildConfig
import com.albertson.codetest.acronymfinder.network.AcromineApi
import com.albertson.codetest.acronymfinder.network.NetworkConstants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            this.level = HttpLoggingInterceptor.Level.HEADERS
            this.level = HttpLoggingInterceptor.Level.BODY
            redactHeader("Authorization")
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        return builder
            .addInterceptor(loggingInterceptor)
            .readTimeout(NetworkConstants.CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(NetworkConstants.CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(NetworkConstants.CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(client: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder().baseUrl("http://www.nactem.ac.uk/software/acromine/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
        return builder.build()
    }

    @Provides
    @Singleton
    internal fun provideAcromineApi(
        retrofit: Retrofit
    ): AcromineApi = retrofit.create(AcromineApi::class.java)
}