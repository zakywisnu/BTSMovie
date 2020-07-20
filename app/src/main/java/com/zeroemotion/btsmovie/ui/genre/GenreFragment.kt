package com.zeroemotion.btsmovie.ui.genre

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeroemotion.btsmovie.R
import com.zeroemotion.btsmovie.databinding.FragmentGenreBinding
import kotlinx.android.synthetic.main.fragment_genre.*

class GenreFragment : Fragment() {
    private lateinit var viewModel: GenreViewModel
    private var genreAdapter =
        GenreAdapter(arrayListOf())
    private lateinit var dataBinding: FragmentGenreBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_genre, container, false)
        return dataBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(GenreViewModel::class.java)
        viewModel.fetchGenre()
        rvGenre.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = genreAdapter
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.genre.observe(viewLifecycleOwner, Observer {genres ->
            genres?.let {
                rvGenre.visibility = View.VISIBLE
                genreAdapter.updateListGenre(genres)
            }
        })
        viewModel.genreError.observe(viewLifecycleOwner, Observer { errors ->
            errors?.let {
                genreError.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        viewModel.genreLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                genreLoading.visibility = if (it) View.VISIBLE else View.GONE
                if (it){
                    rvGenre.visibility = View.GONE
                    genreError.visibility = View.GONE
                }
            }
        })
    }
}