package com.example.kotlin_telsracode.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

//Json structure followed, its a model class
class Row : Serializable{

    @SerializedName("id")
    @Expose
    var rowId : Int =0

    @SerializedName("title")
    @Expose
    var title: String? = null


    @SerializedName("description")
    @Expose
    var description: String? = null


    @SerializedName("imageHref")
    @Expose
    var imageHref: String? = null


    constructor(){}

    constructor(description:String ,title:String ,imageHref:String){
//        this.rowId = rowId
        this.title = title
        this.description = description
        this.imageHref = imageHref

    }

}