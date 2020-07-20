package com.zeroemotion.btsmovie.data.source.response

import com.google.gson.annotations.SerializedName
import com.zeroemotion.btsmovie.data.model.Genre

data class GenreResponse(
    @SerializedName("genres")
    val genre: ArrayList<Genre>?
)