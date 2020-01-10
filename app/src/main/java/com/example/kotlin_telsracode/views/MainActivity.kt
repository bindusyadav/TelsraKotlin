package com.example.kotlin_telsracode.views

import android.content.Context
import android.database.Cursor
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlin_telsracode.R
import com.example.kotlin_telsracode.apiCall.ApiClass
import com.example.kotlin_telsracode.database.DataBaseHandler
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
    private var mDataBaseHandler: DataBaseHandler? = null
    var mCursor:Cursor?=null
    var mHandler : Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mNo_Internet  = findViewById<TextView>(R.id.no_internet)
        val mSwipeRefreshLayout : SwipeRefreshLayout = findViewById(R.id.Swipe_refresh)
        mDataBaseHandler = DataBaseHandler(this)
        // open to read and write
        mDataBaseHandler!!.writableDatabase
        mHandler = Handler()
        mCursor = mDataBaseHandler!!.viewData()



        //network condition check for Offline and Online data.
        networkCondition()


//        swipe-to-refresh the contents on recyclerview
        mSwipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            Log.d("REFRESH....starts", " REFRESHING.........")
            networkCondition()
            mSwipeRefreshLayout.isRefreshing = false
            Log.d("REFRESH...end", " REFRESHED")
        })

    }


    private fun networkCondition() {
        if (isOnline(context = this)) {
            recyler_view.visibility = View.VISIBLE
            no_internet.visibility = View.GONE
            mApiClass = ApiClass(baseContext, this)
            mApiClass!!.parseJson()
            Log.d("INTERNET", " ONLINE ")
        } else{
            if (mCursor!!.count != 0) {
                recyler_view.visibility = View.VISIBLE
                no_internet.visibility = View.GONE
                mApiClass = ApiClass(baseContext, this)
                mApiClass!!.parseJson()
                Log.d("INTERNET", " OFFLINE ")
                mDataBaseHandler!!.getAll()
            } else  {
                recyler_view.visibility = View.GONE
                no_internet!!.visibility = View.VISIBLE
                Log.d("OFFLINE", " NO DATA ")
            }

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
        title = exploreVisitModelClass.title.toString()
        mExploreVisitAdapter = ExploreVisitAdapter(this, exploreVisitModelClass.rows!!, this)

        recyler_view!!.setHasFixedSize(true)
        recyler_view!!.layoutManager = LinearLayoutManager(this@MainActivity)
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
        fragmentTransaction.commit()

    }

}
