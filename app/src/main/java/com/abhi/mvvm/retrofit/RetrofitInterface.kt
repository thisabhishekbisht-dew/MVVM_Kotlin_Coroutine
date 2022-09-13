package com.abhi.mvvm.retrofit

import com.abhi.mvvm.model.Movie
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitInterface {
    @GET("movielist.json")
    suspend fun getAllMovies(): Response<List<Movie>>
}