package com.example.lovecounter2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    private val retrofit =
        Retrofit.Builder().baseUrl("https://love-calculator.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    var apishka = retrofit.create(LoveApi::class.java)


}