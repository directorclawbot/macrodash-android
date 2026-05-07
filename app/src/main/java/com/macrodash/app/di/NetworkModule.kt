package com.macrodash.app.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.macrodash.app.data.remote.ai.OllamaApiService
import com.macrodash.app.data.remote.ai.OpenAIApiService
import com.macrodash.app.data.remote.fred.FredApiService
import com.macrodash.app.data.remote.yahoo.YahooDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @Named("fred")
    fun provideFredRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.stlouisfed.org/fred/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    @Named("yahoo")
    fun provideYahooRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://query1.finance.yahoo.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    @Named("ollama")
    fun provideOllamaRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://localhost:11434/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    @Named("openai")
    fun provideOpenAIRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openai.com/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideFredApiService(@Named("fred") retrofit: Retrofit): FredApiService {
        return retrofit.create(FredApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideYahooDataSource(@Named("yahoo") retrofit: Retrofit): YahooDataSource {
        return retrofit.create(YahooDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideOllamaApiService(@Named("ollama") retrofit: Retrofit): OllamaApiService {
        return retrofit.create(OllamaApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOpenAIApiService(@Named("openai") retrofit: Retrofit): OpenAIApiService {
        return retrofit.create(OpenAIApiService::class.java)
    }
}