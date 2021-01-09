package com.dmikhov.data.di

import com.dmikhov.data.BuildConfig
import com.dmikhov.data.MoneyRepositoryImpl
import com.dmikhov.data.MoviesRepositoryImpl
import com.dmikhov.data.web.WebMovieService
import com.dmikhov.data.web.retrofit.RetrofitMovieServiceApi
import com.dmikhov.data.web.WebConstants
import com.dmikhov.data.web.retrofit.RetrofitMovieServiceImpl
import com.dmikhov.domain.repository.MoneyRepository
import com.dmikhov.domain.repository.MoviesRepository
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    single<Converter.Factory> {
        GsonConverterFactory.create(GsonBuilder().create())
    }
    single<OkHttpClient> {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(WebConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(WebConstants.READ_TIMEOUT, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            httpClientBuilder.addNetworkInterceptor(StethoInterceptor())
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }
        httpClientBuilder.build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(WebConstants.MOVIEDB_BASE_URL)
            .addConverterFactory(get())
            .client(get())
            .build()
    }
    single<RetrofitMovieServiceApi> {
        get<Retrofit>().create(RetrofitMovieServiceApi::class.java)
    }
    single<WebMovieService> {
        RetrofitMovieServiceImpl(get())
    }
}

val repositoryModule = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(get())
    }
    single<MoneyRepository> {
        MoneyRepositoryImpl(androidContext())
    }
}