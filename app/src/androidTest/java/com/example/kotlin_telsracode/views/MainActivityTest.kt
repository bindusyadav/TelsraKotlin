package com.example.kotlin_telsracode.views

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kotlin_telsracode.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
     var mainActivity = MainActivity()


    @Before
    fun launchActivity() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun recycleTest(){
        onView(withId(R.id.recyler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                click()))
    }


    fun networkCondition(): Boolean {
        var status = false
        try {
            val cm =
                mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            var netInfo = cm!!.getNetworkInfo(0)
            if (netInfo != null && netInfo.state == NetworkInfo.State.CONNECTED) {
                status = true
            } else {
                netInfo = cm.getNetworkInfo(1)
                if (netInfo != null && netInfo.state == NetworkInfo.State.CONNECTED) status =
                    true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return status
    }
}