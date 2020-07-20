package com.zeroemotion.btsmovie.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zeroemotion.btsmovie.R
import com.zeroemotion.btsmovie.data.model.Movie
import com.zeroemotion.btsmovie.databinding.ItemMovieBinding
import com.zeroemotion.btsmovie.util.CustomOnClick
import com.zeroemotion.btsmovie.util.getProgressDrawable
import com.zeroemotion.btsmovie.util.loadImage
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter (private val movieList: ArrayList<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(),
    CustomOnClick {

    fun updateMoveList(newList: List<Movie>){
        movieList.clear()
        movieList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemMovieBinding>(inflater, R.layout.item_movie, parent, false)
        return MovieViewHolder(
            view
        )
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.movie = movieList[position]
//        holder.view.movieId.text = movieList[position].id.toString()
        holder.view.listener = this
        holder.view.movieImageView.loadImage(
            movieList[position].posterPath,
            getProgressDrawable(holder.view.movieImageView.context)
        )
    }

    override fun onViewClicked(v: View) {
//        val movieId = v.movieId.text.toString().toInt()
//        val action = MovieFragmentDirections.actionDetailFragment()
//        action.idMovie = movieId
//        Navigation.findNavController(v).navigate(action)

        for (movie in movieList){
            if (v.tag == movie.title){
                val action = MovieFragmentDirections.actionDetailFragment(movie)
                Navigation.findNavController(v).navigate(action)
            }
        }
    }

    class MovieViewHolder(var view: ItemMovieBinding): RecyclerView.ViewHolder(view.root) {

    }

}