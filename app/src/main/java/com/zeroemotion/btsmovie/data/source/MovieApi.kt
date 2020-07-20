package com.zeroemotion.btsmovie.data.source

import com.zeroemotion.btsmovie.BuildConfig.API_KEY
import com.zeroemotion.btsmovie.data.source.response.GenreResponse
import com.zeroemotion.btsmovie.data.source.response.MovieResponse
import com.zeroemotion.btsmovie.data.source.response.ReviewResponse
import com.zeroemotion.btsmovie.data.source.response.TrailerResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("genre/movie/list?api_key=$API_KEY")
    fun getMovieGenre(): Observable<GenreResponse>

    @GET("discover/movie?api_key=$API_KEY&")
    fun getMovieListByGenre(@Query("with_genre") with_genre: Int?): Single<MovieResponse>

    @GET("movie/{movie_id}?api_key=$API_KEY")
    fun getMovieDetail(@Path("movie_id") movie_id: Int?): Single<MovieResponse>

    @GET("movie/{movie_id}/videos?api_key=$API_KEY")
    fun getTrailers(@Path("movie_id") movie_id: Int?): Observable<TrailerResponse>

    @GET("movie/{movie_id}/reviews?api_key=$API_KEY&language=en-US&page=1")
    fun getReviews(@Path("movie_id") movie_id: Int?): Observable<ReviewResponse>
}