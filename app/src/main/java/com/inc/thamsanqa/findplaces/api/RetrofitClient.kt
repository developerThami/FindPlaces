package com.inc.thamsanqa.findplaces.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://maps.googleapis.com/"
    private val instance = getInstance()

    private val retrofitInstance: Retrofit
        get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

    private val gson: Gson
        get() = GsonBuilder().setLenient().create()

    fun getInstance(): Retrofit {
        return if (instance != null) instance else retrofitInstance
    }
}
