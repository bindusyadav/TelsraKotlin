package com.example.kotlin_telsracode.views

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_telsracode.ApiCall.ApiClass
import com.example.kotlin_telsracode.R
import com.example.kotlin_telsracode.model.ExploreVisitModelClass
import com.example.kotlin_telsracode.model.Row
import com.example.kotlin_telsracode.viewmodels.ExploreVisitViewModel
import kotlinx.android.synthetic.main.activity_main.*

//First Screen of the app starts here
//For Test cases refer MainActivityTest
class  MainActivity : AppCompatActivity(),ExploreVisitAdapter.OnVisitListItemListener,
    ExploreVisitViewModel {

    private var mExploreVisitAdapter: ExploreVisitAdapter? = null
    private var mApiClass: ApiClass? = null
    var mTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.kotlin_telsracode.R.layout.activity_main)

        //to save the data
        var sharedPreference: SharedPreferenceVisit = SharedPreferenceVisit(this)

        //network checkcondidtion for Offline and Online.

        if (isOnline(context = this)) {
            recyler_view.visibility = View.VISIBLE
            no_internet.visibility = View.GONE
            mApiClass = ApiClass(baseContext, this)
            mApiClass!!.parseJson()
        } else if (sharedPreference != null) {
            recyler_view.visibility = View.VISIBLE
            no_internet.visibility = View.GONE
            mApiClass = ApiClass(baseContext, this)
            mApiClass!!.parseJson()
            sharedPreference.getValueString("EXPLORELIST")
        } else {
            recyler_view.visibility = View.GONE
            no_internet.visibility = View.VISIBLE
        }
    }

    //method called for network check
    fun isOnline(context: Context): Boolean {
        val connectionManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    //this is a interface called to bind the data to recyclerview
    override fun dataBinding(exploreVisitModelClass: ExploreVisitModelClass) {
        Log.d("BINDU", "exploreVisitModelClass " + exploreVisitModelClass.title)
        setTitle(exploreVisitModelClass.title.toString())
        mExploreVisitAdapter = ExploreVisitAdapter(this, exploreVisitModelClass.rows!!, this)


        recyler_view!!.setHasFixedSize(true)
        recyler_view!!.setLayoutManager(LinearLayoutManager(this@MainActivity))
        recyler_view!!.adapter = mExploreVisitAdapter
    }

    // interface onItemclik called here to send the values form Activity to fragment
    override fun onItemClick(mExploreVisitViewModelArraylist: Row, position: Int){
        Toast.makeText(this, mExploreVisitViewModelArraylist.description, Toast.LENGTH_SHORT).show()
        val selectItemFragment = SelectItemFragment()
        val data = Bundle()
        data.putSerializable("ROW", mExploreVisitViewModelArraylist)
        selectItemFragment.arguments = data

        linear_layout.visibility = View.GONE
        val fragmentManager = this.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_frame, selectItemFragment)
        //fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()



    }

    // i used refresh botton to refresh, in document mention refresh button or refreshing using swip
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menu!!.findItem(R.id.refresh_btn).isVisible = true
        menu.findItem(R.id.refresh_btn).setOnMenuItemClickListener {
         isOnline(this)
            return@setOnMenuItemClickListener true
        }
        return super.onCreateOptionsMenu(menu)
    }
}