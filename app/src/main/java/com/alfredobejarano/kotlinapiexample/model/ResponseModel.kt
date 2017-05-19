package com.alfredobejarano.kotlinapiexample.model

/**
 * This class is the equivalent of a Java Bean.
 *
 * It is used by retrofit to automatically map the values from the JSON to this object to be used in the App.
 */

data class ResponseModel(val origin: String, val url: String)