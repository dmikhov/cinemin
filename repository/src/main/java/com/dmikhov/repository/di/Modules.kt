package com.dmikhov.repository.di

import com.dmikhov.repository.BuildConfig
import com.dmikhov.repository.MoviesRepository
import com.dmikhov.repository.web.IWebMovieService
import com.dmikhov.repository.web.retrofit.RetrofitMovieServiceApi
import com.dmikhov.repository.web.WebConstants
import com.dmikhov.repository.web.retrofit.RetrofitMovieService
import com.dmikhov.usecases.repository.IMoviesRepository
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    single<IWebMovieService> {
        RetrofitMovieService(get())
    }
}

val repositoryModule = module {
    single<IMoviesRepository> {
        MoviesRepository(get())
    }
}