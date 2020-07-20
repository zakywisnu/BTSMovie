package com.zeroemotion.btsmovie.ui.movie

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zeroemotion.btsmovie.data.model.Movie
import com.zeroemotion.btsmovie.data.source.MovieService
import com.zeroemotion.btsmovie.data.source.response.MovieResponse
import com.zeroemotion.btsmovie.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MovieViewModel(application: Application): BaseViewModel(application){
    val movie = MutableLiveData<ArrayList<Movie>>()
    val movieLoading = MutableLiveData<Boolean>()
    val movieError = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    private val movieService =
        MovieService()

    fun fetchMovie(id: Int?){
        movieLoading.value = true
        disposable.add(
            movieService.getMovieListByGenre(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieResponse>(){
                    override fun onSuccess(t: MovieResponse) {
                        movieLoading.value = false
                        movie.value = t.results
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        movieLoading.value = false
                        movieError.value = true
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}