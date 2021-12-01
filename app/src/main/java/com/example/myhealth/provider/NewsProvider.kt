package com.example.myhealth.provider

import com.example.myhealth.model.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "6c36281cfb464a4db6d66995eb0582c1"

interface NewsProvider {

    @GET("top-headlines?country=mx&category=health&apiKey=$API_KEY")
    suspend fun topHeadLines(@Query("country") country: String): Response<NewsApiResponse>
}