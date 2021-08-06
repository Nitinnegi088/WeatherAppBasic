package com.example.weatherapp.Repository

import com.example.weatherapp.Dataclasses.APIWeatherDataclass
import com.example.weatherapp.Network.ApiServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiServiceImp: ApiServiceImp) {

    public fun getCityData(city: String): Flow<APIWeatherDataclass> = flow {
        val response = apiServiceImp.getCityData(city,"fe3b116abd275002914441849822bd61")
        emit(response)
    }.flowOn(Dispatchers.IO)
        .conflate()
}