package com.dmikhov.domain.di

import com.dmikhov.domain.CalculateWeightedPriceUseCaseImpl
import com.dmikhov.domain.MovieDetailsUseCaseImpl
import com.dmikhov.domain.SearchMoviesUseCaseImpl
import com.dmikhov.domain.abs.CalculateWeightedPriceUseCase
import com.dmikhov.domain.abs.MovieDetailsUseCase
import com.dmikhov.domain.abs.SearchMoviesUseCase
import org.koin.dsl.module

val usecaseModule = module {
    factory<SearchMoviesUseCase> { SearchMoviesUseCaseImpl(get()) }
    factory<MovieDetailsUseCase> { MovieDetailsUseCaseImpl(get()) }
    factory<CalculateWeightedPriceUseCase> { CalculateWeightedPriceUseCaseImpl(get()) }
}