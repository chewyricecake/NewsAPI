package com.example.newsapi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapi.Models.Article
import com.example.newsapi.Models.News
import com.example.newsapi.databinding.NewsElementBinding

class RecyclerAdapter (val news:News): RecyclerView.Adapter<RecyclerAdapter.articleViewHolder>(){

    var onItemClick : ((Article)-> Unit)? = null

    inner class articleViewHolder(val binding: NewsElementBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): articleViewHolder {
        return articleViewHolder(
            NewsElementBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: articleViewHolder, position: Int) {
        var article = news.articles[position]
        holder.binding.apply{
            /*author.text = article.author
            content.text = article.content
            description.text = article.description
            publishedAt.text = article. publishedAt
            source.text = article.source.toString()
            url.text = article.url*/
            source.text = article.source.name
            title.text = article.title
            urlToImage.load(article.urlToImage)
        }
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(article)
        }
    }

    override fun getItemCount(): Int {
        return news.articles.size
    }
}