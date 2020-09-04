package com.example.androidbestpractice.di.networkManager

import com.example.androidbestpractice.entity.DataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("/3/discover/movie?api_key=f022aed657b3d6468f1ccee74b2038dd&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    suspend fun getDiscover(): DataResponse

    @GET("/3/search/movie?api_key=f022aed657b3d6468f1ccee74b2038dd&language=en-US&page=1&include_adult=false")
    suspend fun search(@Query("query") query: String): DataResponse
}