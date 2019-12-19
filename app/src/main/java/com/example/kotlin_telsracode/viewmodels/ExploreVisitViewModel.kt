package com.example.kotlin_telsracode.viewmodels

import com.example.kotlin_telsracode.model.ExploreVisitModelClass


// this method called to bind the values to Recyclerview

interface ExploreVisitViewModel {
  fun dataBinding(exploreVisitModelClass: ExploreVisitModelClass)
}
