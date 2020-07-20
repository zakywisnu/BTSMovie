package com.zeroemotion.btsmovie.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.zeroemotion.btsmovie.R
import com.zeroemotion.btsmovie.databinding.FragmentMovieBinding
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var dataBinding: FragmentMovieBinding
    private var movieAdapter =
        MovieAdapter(arrayListOf())
    private var idGenre = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        arguments?.let {
            idGenre = MovieFragmentArgs.fromBundle(
                it
            ).genreId
        }

        viewModel.fetchMovie(idGenre)

        rvMovie.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = movieAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.movie.observe(viewLifecycleOwner, Observer { movies ->
            movies?.let {
                rvMovie.visibility = View.VISIBLE
                movieAdapter.updateMoveList(movies)
            }
        })

        viewModel.movieLoading.observe(viewLifecycleOwner, Observer { loader ->
            loader?.let {
                movieLoading.visibility = if (it) View.VISIBLE else View.GONE
                if (it){
                    rvMovie.visibility = View.GONE
                    movieError.visibility = View.GONE
                }
            }
        })
        viewModel.movieError.observe(viewLifecycleOwner, Observer { errors ->
            errors?.let {
                movieError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }
}