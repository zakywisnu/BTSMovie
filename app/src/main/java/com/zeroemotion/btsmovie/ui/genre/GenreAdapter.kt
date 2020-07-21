package com.zeroemotion.btsmovie.ui.genre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zeroemotion.btsmovie.R
import com.zeroemotion.btsmovie.data.model.Genre
import com.zeroemotion.btsmovie.databinding.ItemGenreBinding
import com.zeroemotion.btsmovie.util.CustomOnClick
import kotlinx.android.synthetic.main.item_genre.view.*

class GenreAdapter(val listGenre: ArrayList<Genre>) :
    RecyclerView.Adapter<GenreAdapter.GenreViewHolder>(),
    CustomOnClick {

    fun updateListGenre(newGenre: List<Genre>) {
        listGenre.clear()
        listGenre.addAll(newGenre)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemGenreBinding>(inflater, R.layout.item_genre, parent, false)
        return GenreViewHolder(
            view
        )
    }

    override fun getItemCount() = listGenre.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.view.genre = listGenre[position]
        holder.view.listener = this
    }

    override fun onViewClicked(v: View) {
        val idGenre = v.genreId.text.toString().toInt()
        val action = GenreFragmentDirections.actionMovieFragment()
        action.genreId = idGenre
        Toast.makeText(v.context, "Genre Id $idGenre", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).navigate(action)
    }

    class GenreViewHolder(var view: ItemGenreBinding) : RecyclerView.ViewHolder(view.root) {

    }


}