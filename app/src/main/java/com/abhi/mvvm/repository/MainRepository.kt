package com.abhi.mvvm.repository

import com.abhi.mvvm.retrofit.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllMovies() = retrofitService.getAllMovies()

}