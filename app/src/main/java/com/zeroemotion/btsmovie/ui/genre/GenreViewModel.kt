package com.zeroemotion.btsmovie.ui.genre

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zeroemotion.btsmovie.data.model.Genre
import com.zeroemotion.btsmovie.data.source.MovieService
import com.zeroemotion.btsmovie.data.source.response.GenreResponse
import com.zeroemotion.btsmovie.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class GenreViewModel(application: Application) : BaseViewModel(application) {
    val genre = MutableLiveData<ArrayList<Genre>>()
    val genreLoading = MutableLiveData<Boolean>()
    val genreError = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    private val movieService =
        MovieService()

    fun fetchGenre() {
        genreLoading.value = true
        disposable.add(
            movieService.getMovieGenre()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<GenreResponse>() {
                    override fun onComplete() {
                        genreLoading.value = false
                    }

                    override fun onNext(t: GenreResponse) {
                        genre.value = t.genre
                    }

                    override fun onError(e: Throwable) {
                        genreLoading.value = false
                        genreError.value = true
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}