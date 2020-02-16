package com.mozay.movie.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mozay.movie.R
import com.mozay.movie.domain.model.movie.response.MovieLite
import com.mozay.movie.util.Constants

class Adapter(private val countryList: List<MovieLite>,
              private val itemClickListener: OnItemClickListener)
    : RecyclerView.Adapter<Adapter.ModelViewHolder>() {

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val movieTitle: TextView = view.findViewById(R.id.movieTitle)
        private val movieImage: ImageView = view.findViewById(R.id.movieImage)

        fun bindItems(item: MovieLite, itemClickListener : OnItemClickListener) {
            movieTitle.text = item.movieTitle
            Glide.with(movieImage.context)
                .load(Constants.POSTER_BASE_URL + item.moviePoster)
                .into(movieImage)
            itemView.setOnClickListener { itemClickListener.onItemClick(item.movieId, item.moviePoster) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ModelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(countryList[position], itemClickListener)
    }

    interface OnItemClickListener {
        fun onItemClick(movieId: Int, movieTempPoster : String)
    }
}