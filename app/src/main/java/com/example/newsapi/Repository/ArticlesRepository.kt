package com.example.newsapi.Repository

import com.example.newsapi.Services.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.example.newsapi.Extra.Utils.API_KEY
import com.example.newsapi.Models.News

class ArticlesRepository {

    fun getArticlesFromServer(topic: String) : Flow<News> = flow{
        emit(RetrofitInstance.ApiInterface.getArticles(topic,API_KEY))
    }.flowOn(Dispatchers.Main)
}