package com.alfredobejarano.kotlinapiexample.network

import com.alfredobejarano.kotlinapiexample.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * This interface defines the endpoint of the API and makes the relation between an Endpoint and a Method.
 *
 * This interface is used in the Mapper class, while building the Retrofit client.
 */
interface APIInterface {
    @GET("get")
    fun getData(): Call<ResponseModel>
}

