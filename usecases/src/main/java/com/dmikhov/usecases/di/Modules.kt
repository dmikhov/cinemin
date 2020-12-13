package com.dmikhov.usecases.di

import com.dmikhov.usecases.CalculateWeightedPriceUseCase
import com.dmikhov.usecases.MovieDetailsUseCase
import com.dmikhov.usecases.SearchMoviesUseCase
import org.koin.dsl.module

val usecaseModule = module {
    factory { SearchMoviesUseCase(get()) }
    factory { MovieDetailsUseCase(get()) }
    factory { CalculateWeightedPriceUseCase(get()) }
}