package com.example.sampledaggerexamples.di

import com.example.sampledaggerexamples.MainActivity
import com.example.sampledaggerexamples.retrofit.RetrofitClient
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitClient::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)


}