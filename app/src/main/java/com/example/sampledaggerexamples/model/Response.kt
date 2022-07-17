package com.example.sampledaggerexamples.model

sealed class Response<T>(val loadingData : Boolean ?=null,val data : T ?=null, val errorMessage  :String ?=null) {
    class Loading<T>(loadingData : Boolean?=null) : Response<T>(loadingData = loadingData)
    class Success<T>(data : T?=null) : Response<T>(data = data)
    class Error<T>(errorMessage : String?=null)  : Response<T>(errorMessage = errorMessage)
}