package com.alfredobejarano.kotlinapiexample.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import com.alfredobejarano.kotlinapiexample.R
import com.alfredobejarano.kotlinapiexample.model.ResponseModel
import com.alfredobejarano.kotlinapiexample.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * This is the main (and only) Activity of the App, it displays the URL used for the HTTP call if
 * the response is valid, or the cause of an error, if something happened during the call.
 */
class MainActivity : AppCompatActivity() {

    /**
     * alled when the activity is starting. This is where most initialization should go: calling setContentView(int) to inflate the activity's UI, using findViewById to programmatically
     * interact with widgets in the UI, calling managedQuery(Uri, String[], String, String[], String) to retrieve cursors for data being displayed, etc.
     *
     * You can call finish from within this function, in which case onDestroy() will be immediately called without any of the rest of the activity lifecycle
     * (onStart, onResume, onPause, etc) executing.
     *
     * Derived classes must call through to the super class's implementation of this method. If they do not, an exception will be thrown.
     *
     * Overrides:
     * onCreate in class AppCompatActivity
     *
     * Parameters:
     * @param savedInstanceState - If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState. Note: Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Thread { MainPresenter(this) }.run()
    }

    /**
     * This method fills the widgets with the Data retrieved by the call.
     */
    fun setup(data: Any) {
        mainProgressBar.visibility = GONE
        if (data is ResponseModel) {
            mainTextView.text = data.url
        } else {
            mainTextView.text = data.toString()
        }
    }
}
