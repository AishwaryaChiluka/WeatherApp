package com.example.sampledaggerexamples



import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sampledaggerexamples.databinding.ActivityMainBinding
import com.example.sampledaggerexamples.di.AppApplication
import com.example.sampledaggerexamples.model.Response
import com.example.sampledaggerexamples.viewmodel.WeatherViewModel
import com.example.sampledaggerexamples.viewmodel.WeatherViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    val TAG  : String = " MainActivity"
    lateinit var weatherViewModel : WeatherViewModel
    private var _binding: ActivityMainBinding? = null

    @Inject
    lateinit var weatherViewModelFactory: WeatherViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        (application as AppApplication).applicationComponent.inject(this)

        initViewModel()

            observeWeatherdetails()

    }


    private fun initViewModel() {
        weatherViewModel = ViewModelProvider(this,weatherViewModelFactory).get(WeatherViewModel::class.java)
    }
    private fun  observeWeatherdetails() {
        weatherViewModel.getWeatherDetails()!!.observe(this, Observer {
            Log.d(TAG,it.toString())
            when(it){
                is Response.Loading -> {
                    _binding?.progressBar?.visible(it.loadingData)
                    _binding?.weathericon?.IconVisibilitVisible(it.loadingData)
                }
                is Response.Success ->{
                    it.data?.let {
                        _binding?.weather = it

                    }
                }
                is Response.Error ->{
                    window.decorView.rootView?.snackbar(it.errorMessage)
                }
            }


        })
    }
}