package com.alfredobejarano.kotlinapiexample.service

import com.alfredobejarano.kotlinapiexample.model.ResponseModel
import com.alfredobejarano.kotlinapiexample.presenter.MainPresenter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class makes the call to the URL and parses the JSON response to a DTO class.
 */
class ResponseModelService() : Callback<ResponseModel> {

    var mainPresenter: MainPresenter? = null

    constructor(mainPresenter: MainPresenter) : this() {
        this.mainPresenter = mainPresenter
        buildApiCall()
    }

    /**
     * Invoked for a received HTTP response.
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call Response.isSuccessful() to determine if the response indicates success.
     */
    override fun onResponse(call: Call<ResponseModel>?, response: Response<ResponseModel>?) {
        if (response != null) {
            if (response.isSuccessful) {
                mainPresenter?.onResponse(response.body()!!)
            }
        }
    }

    /**
     * Invoked when a network exception occurred talking to the server
     * or when an unexpected exception occurred creating the request or processing the response.
     */
    override fun onFailure(call: Call<ResponseModel>?, t: Throwable?) {
        mainPresenter?.onError(t.toString())
    }

    /**
     * This method builds an OkHTTP Client to be used as an interceptor to the Retrofit Client.
     *
     * This is useful to debug the response of the URL.
     */
    fun buildOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val httpLogginInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        httpLogginInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.networkInterceptors().add(httpLogginInterceptor)
        return builder.build()
    }

    /**
     * This method builds the Retrofit client to make the Call to the URL and retrieve a JSON.
     *
     * The syntax in Kotlin is the same as in JAVA.
     */
    fun buildApiCall() {
        val httpRestClient: Retrofit = Retrofit.Builder()
                .baseUrl("https://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(buildOkHttpClient())
                .build()

        val getData: ResponseModelServiceAPI = httpRestClient.create(ResponseModelServiceAPI::class.java)
        val apiCall: Call<ResponseModel> = getData.getData()

        apiCall.enqueue(this)
    }
}
