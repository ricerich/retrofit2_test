package com.example.retrofit2_test

import retrofit2.Call
import retrofit2.http.GET

interface PeopleService {
//    @GET("people/1")
    @GET("testWeb/testFile.jsp")
    fun getPeople(): Call<People>
}