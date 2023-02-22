package com.example.newsapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.example.newsapi.Models.Article
import com.example.newsapi.databinding.ActivityDetailBinding
import com.example.newsapi.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val article = intent.getParcelableExtra<Article>("Article")
        if (article!=null){
            binding.source.text = article.source.name
            binding.title.text = article.title
            binding.description.text = article.description
            binding.author.text = article.author
            binding.date.text = article.publishedAt
            binding.urlToImage.load(article.urlToImage)
            binding.content.text = article.content
            binding.URL.text = article.url
        }

        binding.backArrow.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }


    }
}