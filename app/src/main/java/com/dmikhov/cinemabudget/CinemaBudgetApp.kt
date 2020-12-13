package com.dmikhov.cinemabudget

import android.app.Application
import com.dmikhov.di.viewModelModule
import com.dmikhov.repository.di.apiModule
import com.dmikhov.repository.di.repositoryModule
import com.dmikhov.usecases.di.usecaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CinemaBudgetApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CinemaBudgetApp)
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