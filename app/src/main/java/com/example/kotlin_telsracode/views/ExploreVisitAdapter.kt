package com.example.kotlin_telsracode.views

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_telsracode.R
import com.example.kotlin_telsracode.model.Row
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_listdetails.view.*

// RecyclerView Adapter, used  to get subTitle ,description and image for Title(About Canada)
class ExploreVisitAdapter(val context: Context, val mExploreVisitViewModelArraylists: List<Row>,var clickListener:OnVisitListItemListener) :
    RecyclerView.Adapter<ExploreVisitAdapter.CardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_listdetails, parent, false)

        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mExploreVisitViewModelArraylists.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.initialize(mExploreVisitViewModelArraylists.get(position),clickListener)
    }


    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mImageView = itemView.image_view
        var mSubtitle_textView = itemView.subTitle
        var mDescription_textView = itemView.description

      fun initialize(mExploreVisitViewModelArraylist:Row, action:OnVisitListItemListener){

         if( !mExploreVisitViewModelArraylist.title.equals(null)) {
             mSubtitle_textView.text = mExploreVisitViewModelArraylist.title
         }else{
             mSubtitle_textView.setText("No Json response found")
         }


          if( !mExploreVisitViewModelArraylist.description.equals(null)) {
              mDescription_textView.text = mExploreVisitViewModelArraylist.description
          }else{
              mDescription_textView.setText("No Json response found")
          }


          Picasso.get().load(mExploreVisitViewModelArraylist.imageHref).into(mImageView)

          if (mExploreVisitViewModelArraylist.imageHref != "") {
              Picasso.get().load(mExploreVisitViewModelArraylist.imageHref)
                  .placeholder(R.mipmap.ic_launcher).into(mImageView, object : Callback {
                  override fun onSuccess() {
                      Log.d("Bindu", "onSuccess ")
                      Picasso.get().load(mExploreVisitViewModelArraylist.imageHref)
                          .into(mImageView)
                  }

                  override fun onError(e: Exception) {
                      Log.d("Bindu", "onError $e")
                  }
              })
          }

          itemView.setOnClickListener(View.OnClickListener {
              action.onItemClick(mExploreVisitViewModelArraylist,adapterPosition)
          })
      }
    }

    //this interfaxce implemented to implement onclick listener for each items on cardview
    interface OnVisitListItemListener{
        fun onItemClick(mExploreVisitViewModelArraylist: Row,position: Int)
    }
}