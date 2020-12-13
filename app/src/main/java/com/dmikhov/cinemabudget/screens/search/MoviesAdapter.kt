package com.dmikhov.cinemabudget.screens.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dmikhov.cinemabudget.R
import com.dmikhov.entities.movie.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    var onMovieClicked: ((movie: Movie) -> Unit)? = null
    var movies: MutableList<Movie> = ArrayList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(SearchDiffUtil(field, value))
            field.clear()
            field.addAll(value)
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() = with(itemView) {
            val movie = movies[adapterPosition]
            Glide.with(movieImageView).load(movie.posterUrl).into(movieImageView)
            movieTitleTextView.text = movie.title
            movieDetailsTextView.text =
                context.getString(R.string.format_release, movie.releaseDate)
            movieLayout.setOnClickListener {
                onMovieClicked?.invoke(movie)
            }
        }
    }
}