package com.example.kotlin_telsracode.viewtests

import android.view.View
import android.widget.TextView
import com.example.kotlin_telsracode.R
import com.example.kotlin_telsracode.views.SelectItemFragment
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class FragmentTestCases {
    @Mock
    var selectItemFragment : SelectItemFragment = SelectItemFragment()

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()

    @Test
    public fun testselectItemFragment() {
        Assert.assertEquals(false, selectItemFragment.arguments != null)
    }
}