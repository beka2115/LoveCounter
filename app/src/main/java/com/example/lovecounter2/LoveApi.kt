package com.example.lovecounter2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LoveApi {
    @GET("getPercentage")
    fun getLoveResult(
        @Query("fname") firstName: String,
        @Query("sname") secondName: String,
        @Header("X-RapidAPI-Key") key: String = "3db5a800b7mshbc26d77c51b16fcp1219acjsnb47d98bc7360",
        @Header("X-RapidAPI-Host") host: String ="love-calculator.p.rapidapi.com"
    ): Call<LoveModel>
}