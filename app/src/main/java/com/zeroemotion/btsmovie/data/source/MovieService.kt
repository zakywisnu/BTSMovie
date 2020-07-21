package com.zeroemotion.btsmovie.data.source

import com.zeroemotion.btsmovie.BuildConfig.BASE_URL
import com.zeroemotion.btsmovie.data.source.response.GenreResponse
import com.zeroemotion.btsmovie.data.source.response.MovieResponse
import com.zeroemotion.btsmovie.data.source.response.ReviewResponse
import com.zeroemotion.btsmovie.data.source.response.TrailerResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MovieApi::class.java)

    fun getMovieGenre(): Observable<GenreResponse> {
        return retrofit.getMovieGenre()
    }

    fun getMovieListByGenre(genre: Int?): Single<MovieResponse> {
        return retrofit.getMovieListByGenre(genre)
    }

    fun getMovieDetail(id: Int?): Single<MovieResponse> {
        return retrofit.getMovieDetail(id)
    }

    fun getTrailers(id: Int?): Observable<TrailerResponse> {
        return retrofit.getTrailers(id)
    }

    fun getReviews(id: Int?): Observable<ReviewResponse> {
        return retrofit.getReviews(id)
    }
}