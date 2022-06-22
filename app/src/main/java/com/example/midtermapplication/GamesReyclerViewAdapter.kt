package com.example.midtermapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.midtermapp.R
import kotlinx.android.synthetic.main.item_games_recyclerview_layout.view.*

class GamesReyclerViewAdapter(private val games:ArrayList<GameListModel.Result>,private val activity: DashboardActivity) : RecyclerView.Adapter<GamesReyclerViewAdapter.ViewHolder>() {

    override fun getItemCount() = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_games_recyclerview_layout,parent,false))
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private lateinit var model:GameListModel.Result
        fun onBind(){
            model = games[adapterPosition]

            Glide.with(activity).load(model.background_image).into(itemView.gameImage)

            itemView.gameNameTextView.text = "Name: "+model.name.toString()

            itemView.gameReleaseTextView.text = "Release: "+model.released.toString()

            itemView.gameRatingTextView.text = "Rating: "+model.rating_top+"/5"



        }
    }
}