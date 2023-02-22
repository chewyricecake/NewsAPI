package com.example.newsapi.Services

import com.example.newsapi.Models.News
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {
    //@GET("everything?/q={topic}&apiKey={key}")
    @GET("everything")
    suspend fun getArticles(@Query("q") q: String, /*@Query("sortBy") sortBy: String,*/ @Query("apiKey") apiKey: String ): News


}