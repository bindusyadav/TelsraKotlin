package com.example.kotlin_telsracode.viewtests

/**
 * ExploreVisitModelClass local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
import android.view.View
import com.example.kotlin_telsracode.R
import com.example.kotlin_telsracode.model.Row
import com.example.kotlin_telsracode.views.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit


class MainActivityTest {
    @Mock
    var mainActivity: MainActivity = MainActivity()

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()



    @Test
    fun testMainActivityIsOnline() {
        val isOnline = mainActivity.isOnline(context = mainActivity)
        assertEquals(false ,isOnline)
    }

    @Test
    fun testOnItemClick() {
        val row = Row()
        row.title = "title"
        row.description = "description"
        row.imageHref = "image"
        mainActivity.onItemClick(row, 1)
    }

}