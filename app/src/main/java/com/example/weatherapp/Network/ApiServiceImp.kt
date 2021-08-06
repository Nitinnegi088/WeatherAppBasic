package com.example.weatherapp.Network

import com.example.weatherapp.Dataclasses.APIWeatherDataclass
import javax.inject.Inject

class ApiServiceImp @Inject constructor(private val apiService: ApiService) {
            suspend fun getCityData(city: String,appId: String): APIWeatherDataclass =
                apiService.getCityData(city,appId)
}