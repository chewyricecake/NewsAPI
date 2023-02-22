package com.example.newsapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapi.Adapter.RecyclerAdapter
import com.example.newsapi.Extra.Events
import com.example.newsapi.Models.News
import com.example.newsapi.ViewModel.ArticlesViewModel
import com.example.newsapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var topic: String = ""
    val articlesViewModel: ArticlesViewModel by viewModels()

    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //searchView -> fetch the news according to the topic
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                topic = query
                articlesViewModel.get(topic)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        if(topic==""){
            binding.progressbar.visibility = View.INVISIBLE
            binding.message.text = "Input Topic"
        }

        //articlesViewModel.get(topic)
        articlesViewModel.articles.observe(this, Observer {
            when(it){
                is Events.Loading ->{
                    binding.progressbar.visibility = View.VISIBLE
                }
                is Events.Success ->{
                    binding.progressbar.visibility = View.GONE
                    binding.recyclerView.setHasFixedSize(true)
                    binding.recyclerView.layoutManager = LinearLayoutManager(this)

                    recyclerAdapter = it.data?.let { data -> RecyclerAdapter(data) }!!
                    binding.recyclerView.adapter = recyclerAdapter

                    recyclerAdapter.onItemClick = {
                        val intent = Intent(this, DetailActivity::class.java)
                        intent.putExtra("Article", it)
                        startActivity(intent)
                    }

                }
                is Events.Error ->{
                    binding.progressbar.visibility = View.GONE
                    it.let{
                        binding.message.text = it.msg.toString()
                    }
                }
            }
        })
    }


}

