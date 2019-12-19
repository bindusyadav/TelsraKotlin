package com.example.kotlin_telsracode.viewtests

/**
 * ExploreVisitModelClass local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.example.kotlin_telsracode.ApiCall.ApiClass
import com.example.kotlin_telsracode.R
import com.example.kotlin_telsracode.model.ExploreVisitModelClass
import com.example.kotlin_telsracode.model.Row
import com.example.kotlin_telsracode.views.MainActivity
import junit.framework.TestCase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit


class MainActivityTest {
    @Mock
    var mainActivity: MainActivity = MainActivity()

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()



    @Test
    public fun testMainActivityIsOnline() {
        val isOnline = mainActivity.isOnline(context = mainActivity)
        assertEquals(false ,isOnline)
    }

    @Test
    public fun testOnItemClick() {
        val row = Row()
        row.title = "title"
        row.description = "description"
        row.imageHref = "image"
        mainActivity.onItemClick(row, 1)
    }
}