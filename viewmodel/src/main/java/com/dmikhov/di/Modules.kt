package com.dmikhov.di

import com.dmikhov.viewmodel.MovieDetailViewModel
import com.dmikhov.viewmodel.SearchMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchMoviesViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}