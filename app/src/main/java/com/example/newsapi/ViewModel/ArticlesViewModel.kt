package com.example.newsapi.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapi.Extra.Events
import com.example.newsapi.Models.Article
import com.example.newsapi.Models.News
import com.example.newsapi.Repository.ArticlesRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ArticlesViewModel: ViewModel() {

    val articlesRepository: ArticlesRepository = ArticlesRepository()
    val articles : MutableLiveData<Events<News>> = MutableLiveData()

    fun get(topic: String, sort:String){
        viewModelScope.launch {
            articlesRepository.getArticlesFromServer(topic,sort).catch {
                Log.e("VM","get: ${it.localizedMessage}/n")
                articles.postValue(Events.Error(msg = it.localizedMessage))
            }.collect{
                articles.postValue(Events.Success(it))
            }
        }
    }
}