package com.zeroemotion.btsmovie.data.model

import com.google.gson.annotations.SerializedName

data class Trailer(

    @SerializedName("id")
    val movieId: String?,
    val key: String?,
    val name: String?,
    val site: String?
)