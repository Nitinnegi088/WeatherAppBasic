package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.weatherapp.ViewModel.WeatherViewModel
import com.example.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        weatherViewModel.getCityData()
        initListener()
        weatherViewModel.weatherResponse.observe(this, Observer { response ->

            if (response.weather[0].description == "clear sky" || response.weather[0].description == "mist") {
                Glide.with(this)
                    .load(R.drawable.ic_cloud)
                    .into(binding.image)
            } else
                if (response.weather[0].description == "haze" || response.weather[0].description == "overcast clouds" || response.weather[0].description == "fog") {
                    Glide.with(this)
                        .load(R.drawable.ic_cloud)
                        .into(binding.image)
                } else if (response.weather[0].description == "rain") {
                    Glide.with(this)
                        .load(R.drawable.ic_cloud)
                        .into(binding.image)
                }
            binding.description.text = response.weather[0].description
            binding.name.text=response.name
            binding.degree.text=response.wind.deg.toString()
            binding.speed.text=response.wind.speed.toString()
            binding.temp.text=response.main.temp.toString()
            binding.humidity.text=response.main.humidity.toString()
        })
    }


    private fun initListener() {
            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { weatherViewModel.setSearchQuery(it) }
                    Log.d("main", "onQueryTextChange: $query")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    return true
                }

            })
    }
}
