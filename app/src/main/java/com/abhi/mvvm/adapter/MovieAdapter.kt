package com.abhi.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhi.mvvm.databinding.AdapterMovieBinding
import com.abhi.mvvm.listners.OnItemClickListener
import com.abhi.mvvm.model.Movie
import com.abhi.mvvm.util.ValidationUtil
import com.bumptech.glide.Glide

class MovieAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var movieList = mutableListOf<Movie>()
    var listener: OnItemClickListener? = null
    fun setMovies(movies: List<Movie>/*, listener: OnItemClickListener*/) {
        this.movieList = movies.toMutableList()
      /*  this.listener = listener*/
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val movie = movieList[position]
        if (ValidationUtil.validateMovie(movie)) {
            holder.binding.name.text = movie.name
            Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)
        }

        holder.itemView.setOnClickListener {
            listener!!.onItemClick(holder.itemView)
        }

    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

}