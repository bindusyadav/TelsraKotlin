package com.example.kotlin_telsracode.ApiCall

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.kotlin_telsracode.model.ExploreVisitModelClass
import com.example.kotlin_telsracode.viewmodels.ExploreVisitViewModel
import com.example.kotlin_telsracode.views.SharedPreferenceVisit
import com.google.gson.Gson
import org.json.JSONException


class ApiClass(context: Context, mExploreVisitViewModel: ExploreVisitViewModel){
    private var mRequestQueue: RequestQueue? = null
    private var exploreVisitModel : ExploreVisitModelClass? = null
    private var exploreVisitViewModel : ExploreVisitViewModel? = mExploreVisitViewModel
    private var mContext : Context? = context
    val sharedPreference: SharedPreferenceVisit = SharedPreferenceVisit(context)

// Used Gson for making API call and Used Volley for Parsing Json

    fun parseJson() {
        val url = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json"
        mRequestQueue = Volley.newRequestQueue(mContext)
        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener { response ->
                try {
                    val gson = Gson()
                    exploreVisitModel = gson.fromJson(response.toString(), ExploreVisitModelClass::class.java)
                    Log.d("BINDU","exploreVisitModel "+exploreVisitModel!!.title)
                    exploreVisitViewModel!!.dataBinding(exploreVisitModel!!)
                    //saving the values to sharedPreference
                    sharedPreference.save("EXPLORELIST", exploreVisitViewModel!!.toString())
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { })
        mRequestQueue?.add(request)
    }
}