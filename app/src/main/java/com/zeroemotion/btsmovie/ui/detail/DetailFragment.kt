package com.zeroemotion.btsmovie.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zeroemotion.btsmovie.R
import com.zeroemotion.btsmovie.data.model.Movie
import com.zeroemotion.btsmovie.databinding.FragmentDetailBinding
import com.zeroemotion.btsmovie.ui.detail.review.ReviewAdapter
import com.zeroemotion.btsmovie.ui.detail.trailer.TrailerAdapter
import com.zeroemotion.btsmovie.util.getProgressDrawable
import com.zeroemotion.btsmovie.util.loadImage
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    var movies: Movie? = null
    private lateinit var dataBinding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    //        private var idMov = 0
    private var reviewAdapter =
        ReviewAdapter(arrayListOf())
    private var trailerAdapter =
        TrailerAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        arguments?.let {
            movies = DetailFragmentArgs.fromBundle(
                it
            ).movie
        }

        context?.let {
            dataBinding.detailPoster.loadImage(movies?.posterPath, getProgressDrawable(it))
        }

        context?.let {
            dataBinding.backdropMovie.loadImage(movies?.backdropPath, getProgressDrawable(it))
        }

        dataBinding.movieDetail = movies
        viewModel.fetchReview(movies?.id)
        viewModel.fetchTrailer(movies?.id)
        viewModel.fetchDetail(movies?.id)

        rvTrailer.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = trailerAdapter
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }

        rvReviews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reviewAdapter
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.trailer.observe(viewLifecycleOwner, Observer { trailer ->
            trailer?.let {
                rvTrailer.visibility = View.VISIBLE
                trailerAdapter.updateTrailer(trailer)
            }
        })

        viewModel.trailerLoad.observe(viewLifecycleOwner, Observer { trailLoad ->
            trailLoad?.let {
                trailerLoading.visibility = if(it) View.VISIBLE else View.GONE
                if (it) rvTrailer.visibility = View.GONE
            }
        })

        viewModel.review.observe(viewLifecycleOwner, Observer { review ->
            review?.let {
                rvReviews.visibility = View.VISIBLE
                reviewAdapter.updateReview(review)
            }
        })

        viewModel.reviewLoad.observe(viewLifecycleOwner, Observer { reviewLoad ->
            reviewLoad?.let {
                reviewLoading.visibility = if (it) View.VISIBLE else View.GONE
                if (it) rvReviews.visibility = View.GONE
            }
        })
    }

}