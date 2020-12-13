package com.dmikhov.cinemabudget.screens.search

import androidx.recyclerview.widget.DiffUtil
import com.dmikhov.entities.Movie

class SearchDiffUtil(
    private val oldMovies: MutableList<Movie>,
    private val newMovies: MutableList<Movie>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldMovies.size

    override fun getNewListSize(): Int = newMovies.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovies[oldItemPosition].id == newMovies[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovies[oldItemPosition] == newMovies[newItemPosition]
    }
}