package com.example.sampledaggerexamples.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sampledaggerexamples.BuildConfig
import com.example.sampledaggerexamples.model.Response
import com.example.sampledaggerexamples.model.Weather
import com.example.sampledaggerexamples.retrofit.ApiInterface
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val apiInterface: ApiInterface) {

    val weatherData = MutableLiveData<Response<Weather>>()
    val TAG  : String = " WeatherRepository"
    private val API_KEY: String = BuildConfig.API_KEY
    private lateinit var  disposable  : Disposable
    val call =    apiInterface.getWeatherData("hyderabad", API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getWeatherObserverRx())



    private fun getWeatherObserverRx(): Observer<Weather> {
        return object :Observer<Weather>{
            override fun onComplete() {
              weatherData.value = Response.Loading(false)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG,"Weather Error : "+ e)
                var error = e.message.toString()
                if(e is UnknownHostException){
                    error = " Something went wrong"
                }else if(e is  HttpException)
                {
                    error =   (e as HttpException).code().toString()
                   if(error.equals("401"))
                   error = "Please check API Key"
                }


                weatherData.value =  Response.Error(error)
            }

            override fun onNext(weather: Weather) {
                Log.d(TAG,"Weather : "+ weather)
              weatherData.value  = Response.Success(weather)
            }

            override fun onSubscribe(d: Disposable) {
                disposable = d
                weatherData.value = Response.Loading(true)
            }

        }
    }


    fun dispose() {
        disposable.dispose()
    }

}