package com.example.androidbestpractice.entity

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("results") val results: List<DataResult>
)

data class DataResult(@SerializedName("id") val id: Int
                      ,@SerializedName("title") val title: String
                      ,@SerializedName("overview") val overview: String
                      ,@SerializedName("release_date") val release_date: String
                      ,@SerializedName("poster_path") val posterPath: String
                      ,@SerializedName("backdrop_path") val backdropPath: String
                      ,@SerializedName("vote_average") val voteAverage: Float
)