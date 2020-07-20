package com.zeroemotion.btsmovie.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.zeroemotion.btsmovie.data.source.response.ReviewResponse
import com.zeroemotion.btsmovie.data.source.response.TrailerResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: Int?,

    var title: String?,

    val director: String?,

    var overview: String?,

    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("vote_average")
    val voteAverage: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?
): Parcelable