package com.dmikhov.cinemin.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dmikhov.cinemin.R
import com.dmikhov.cinemin.screens.about.AboutFragment
import com.dmikhov.cinemin.screens.movie.MovieFragment
import com.dmikhov.cinemin.screens.search.SearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val isItFirstTimeLaunch = supportFragmentManager.fragments.isEmpty()
        if (isItFirstTimeLaunch) {
            val searchFragment = SearchFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.rootFragmentContainer, searchFragment).commit()
        }

    }

    fun openHomeFragment(movieId: Int?, movieTitle: String?) {
        val homeFragment = MovieFragment.newInstance(movieId, movieTitle)
        supportFragmentManager.beginTransaction()
            .add(R.id.rootFragmentContainer, homeFragment)
            .addToBackStack(null)
            .commit()
    }

    fun openAboutFragment() {
        val aboutFragment = AboutFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.rootFragmentContainer, aboutFragment)
            .addToBackStack(null)
            .commit()
    }
}