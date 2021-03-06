package com.dmikhov.cinemin

import android.app.Application
import com.dmikhov.di.viewModelModule
import com.dmikhov.data.di.apiModule
import com.dmikhov.data.di.repositoryModule
import com.dmikhov.domain.di.usecaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CineminApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CineminApp)
            modules(modules)
        }
    }

    private val modules = listOf(
        apiModule,
        repositoryModule,
        usecaseModule,
        viewModelModule
    )
}