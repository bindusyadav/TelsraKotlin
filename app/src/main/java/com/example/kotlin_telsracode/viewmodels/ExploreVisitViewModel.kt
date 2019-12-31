package com.example.kotlin_telsracode.viewmodels

import com.example.kotlin_telsracode.model.ExploreVisitModelClass
import com.example.kotlin_telsracode.model.Row


// this method called to bind the values to Recyclerview

interface ExploreVisitViewModel {
  fun dataBinding(exploreVisitModelClass: ExploreVisitModelClass)
}
