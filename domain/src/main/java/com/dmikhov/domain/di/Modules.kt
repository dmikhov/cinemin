package com.dmikhov.domain.di

import com.dmikhov.domain.CalculateWeightedPriceUseCase
import com.dmikhov.domain.MovieDetailsUseCase
import com.dmikhov.domain.SearchMoviesUseCase
import org.koin.dsl.module

val usecaseModule = module {
    factory { SearchMoviesUseCase(get()) }
    factory { MovieDetailsUseCase(get()) }
    factory { CalculateWeightedPriceUseCase(get()) }
}