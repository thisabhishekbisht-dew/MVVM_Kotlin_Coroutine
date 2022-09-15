package com.abhi.mvvm.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abhi.mvvm.adapter.MovieAdapter
import com.abhi.mvvm.databinding.ActivityMainBinding
import com.abhi.mvvm.repository.MainRepository
import com.abhi.mvvm.retrofit.RetrofitService
import com.abhi.mvvm.viewModel.MainViewModel
import com.abhi.mvvm.viewModel.MyViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private val adapter = MovieAdapter()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)

        /*app centre*/
        AppCenter.start(
            application, "e898d113-52a2-4afe-9cf2-a3af9876d59d",
            Analytics::class.java, Crashes::class.java
        )
        /*addedd appcentre to this project*/
        binding.recyclerview.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)


        /*image click listner */
        binding.recyclerview.setOnClickListener{
            Snackbar.make(it,"Hello there ",Snackbar.LENGTH_LONG).show()
        }

        viewModel.movieList.observe(this) {
            adapter.setMovies(it)
        }


        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        viewModel.getAllMovies()

    }
}