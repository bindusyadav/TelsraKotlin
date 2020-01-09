package com.example.kotlin_telsracode.viewtests


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
    fun testselectItemFragment() {
        Assert.assertEquals(false, selectItemFragment.arguments != null)
    }
}