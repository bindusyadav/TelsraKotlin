package com.example.kotlin_telsracode.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kotlin_telsracode.R
import com.example.kotlin_telsracode.model.Row
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

//For testcase refer FragmentTestCases

class SelectItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     val view = inflater.inflate(R.layout.fragment_select_item, container, false)
        val myValue = this.arguments!!.getSerializable("ROW")
        Log.d("Bindu", "SELECTEDITEM "+myValue)
        val row:Row = myValue as Row
        Log.d("Bindu", "SELECTEDITEM ROW "+row.description)
        Log.d("Bindu", "SELECTEDITEM ROW "+row.title)
        Log.d("Bindu", "SELECTEDITEM ROW "+row.imageHref)

        val imageView = view.findViewById(R.id.image_update) as ImageView
        val subTitles: TextView = view.findViewById(R.id.sub_title) as TextView
        val descriptions: TextView = view.findViewById(R.id.description_title) as TextView

        subTitles.text = row.title.toString()
        descriptions.text = row.description

        Picasso.get().load(row.imageHref).placeholder(R.mipmap.ic_launcher)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    Log.d("Bindu", "onSuccess ")
                }

                override fun onError(e: Exception) {
                    Log.d("Bindu", "onError $e")
                }
            })

        return view
    }

}
