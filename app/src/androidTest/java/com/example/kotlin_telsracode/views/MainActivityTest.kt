package com.example.kotlin_telsracode.views

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import com.example.kotlin_telsracode.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class MainActivityTest{

    @Rule
    var mainActivityActivityTestRule =
        ActivityTestRule(
            MainActivity::class.java
        )

@Test
 fun recycleTest(){
        onView(withId(R.id.recyler_view)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            click()))
 }


//    fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher? {
//        return RecyclerViewMatcher(recyclerViewId)
//    }
//
//    fun tapRecyclerViewItem(recyclerViewId: Int, position: Int) {
//        onView(withId(recyclerViewId)).perform(scrollTo())
//        onView(withRecyclerView(recyclerViewId).atPosition(position)).perform(click())
//    }

}