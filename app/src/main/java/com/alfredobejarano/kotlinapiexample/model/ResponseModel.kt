package com.alfredobejarano.kotlinapiexample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * This class serves as a Data Transfer Object (DTO).
 *
 * It is used by retrofit to automatically map the values from the JSON to this object to be used in the App.
 */

class ResponseModel {
    @SerializedName("origin")
    @Expose
    var origin: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
}