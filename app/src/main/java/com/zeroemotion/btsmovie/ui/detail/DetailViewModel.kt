package com.zeroemotion.btsmovie.ui.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zeroemotion.btsmovie.data.model.Review
import com.zeroemotion.btsmovie.data.model.Trailer
import com.zeroemotion.btsmovie.data.source.MovieService
import com.zeroemotion.btsmovie.data.source.response.TrailerResponse
import com.zeroemotion.btsmovie.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class DetailViewModel(application: Application) : BaseViewModel(application) {
    val review = MutableLiveData<ArrayList<Review>>()
    val trailer = MutableLiveData<ArrayList<Trailer>>()
    val reviewLoad = MutableLiveData<Boolean>()
    val trailerLoad = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    private val movieService = MovieService()



    fun fetchTrailer(id: Int?){
        trailerLoad.value = true
        disposable.add(
            movieService.getTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<TrailerResponse>(){
                    override fun onComplete() {
                        trailerLoad.value = false
                    }

                    override fun onNext(t: TrailerResponse) {
                        trailer.value = t.results
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })


        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}