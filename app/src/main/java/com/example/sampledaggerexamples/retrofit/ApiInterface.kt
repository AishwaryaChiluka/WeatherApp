package com.example.sampledaggerexamples.retrofit
import com.example.sampledaggerexamples.model.Weather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    @GET("data/2.5/weather?")
    fun getWeatherData(@Query("q") city: String ,@Query("appid")apikey: String): Observable<Weather>

}