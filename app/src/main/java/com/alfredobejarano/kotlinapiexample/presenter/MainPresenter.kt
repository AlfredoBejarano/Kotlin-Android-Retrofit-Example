package com.alfredobejarano.kotlinapiexample.presenter

import com.alfredobejarano.kotlinapiexample.view.activity.MainActivity
import com.alfredobejarano.kotlinapiexample.model.ResponseModel
import com.alfredobejarano.kotlinapiexample.network.mapper.ResponseModelMapper

/**
 * This class handles the data retrieved from the HTTP Call and sends it to the view.
 */
class MainPresenter(view: MainActivity) {
    var view: MainActivity? = view

    /**
     * This method is used by the Mapper when the Call returned a valid response.
     */
    fun onResponse(responseModel: ResponseModel) {
        view?.setup(responseModel)
    }

    /**
     * This method is used by the mapper when the Call returns an error.
     */
    fun onError(error: String) {
        view?.setup(error)
    }

    init {
        ResponseModelMapper(this)
    }
}