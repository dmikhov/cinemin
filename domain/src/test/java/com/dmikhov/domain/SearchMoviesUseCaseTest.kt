package com.dmikhov.domain

import com.dmikhov.domain.entities.Result
import com.dmikhov.domain.repository.MoviesRepository
import com.dmikhov.entities.movie.Movie
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class SearchMoviesUseCaseTest {
    @Test
    fun `must return same movies list provided by repository`() {
        val movieTitle = "Test Movie"
        val testMovie = Movie(0, null, null, null, null, null, null, null, false, null, null)
        val testMovies = listOf(testMovie)
        val testResult = Result(testMovies)
        val moviesRepository = Mockito.mock(MoviesRepository::class.java)
        Mockito.`when`(moviesRepository.searchMovie(movieTitle)).thenReturn(testResult)
        val searchMoviesUseCase = SearchMoviesUseCaseImpl(moviesRepository)
        val resultFromUseCase = searchMoviesUseCase.searchMoviesByTitle(movieTitle)
        Assert.assertEquals(testResult, resultFromUseCase)
    }

    @Test
    fun `must call search method in repository only once`() {
        val movieTitle = "Test Movie"
        val testMovie = Movie(0, null, null, null, null, null, null, null, false, null, null)
        val testMovies = listOf(testMovie)
        val testResult = Result(testMovies)
        val moviesRepository = Mockito.mock(MoviesRepository::class.java)
        Mockito.`when`(moviesRepository.searchMovie(movieTitle)).thenReturn(testResult)
        val searchMoviesUseCase = SearchMoviesUseCaseImpl(moviesRepository)
        searchMoviesUseCase.searchMoviesByTitle(movieTitle)
        Mockito.verify(moviesRepository).searchMovie(movieTitle)
    }
}