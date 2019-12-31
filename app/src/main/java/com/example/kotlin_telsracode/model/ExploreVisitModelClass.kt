package com.example.kotlin_telsracode.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//Json structure followed, model class
class ExploreVisitModelClass {

    @SerializedName("id")
    @Expose
    var id : Int =0

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("rows")
    @Expose
    var rows: List<Row>? = null


}