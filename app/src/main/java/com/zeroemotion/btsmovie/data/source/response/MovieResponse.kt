package com.zeroemotion.btsmovie.data.source.response

import com.google.gson.annotations.SerializedName
import com.zeroemotion.btsmovie.data.model.Movie

data class MovieResponse(
    @SerializedName("results")
    val results: ArrayList<Movie>?,

    @SerializedName("total_results")
    val totalResults: Int?
)