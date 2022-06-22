package com.example.midtermapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midtermapp.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var adapter: GamesReyclerViewAdapter
    private var games  = ArrayList<GameListModel.Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        getGames()
    }

    private fun getGames() {

        dashboardRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = GamesReyclerViewAdapter(games,this)
        dashboardRecyclerView.adapter = adapter

        //we connect videoGameDatabase api and get information about 20 videogames
        GameDataLoader.getGames("20", object : CustomCallBack {
            override fun onSuccess(result: String) {

                //Parse Json body
                var model = Gson().fromJson(result, GameListModel::class.java)

                //Check
                d("game count: ", "${model.results.size}")
                d("First game name: ", "${model.results[0].name}")
                d("First game Image: ", "${model.results[0].background_image}")
                d("First game rating: ", "${model.results[0].rating_top}")
                d("First game Release: ", "${model.results[0].released}")

                //add Parsed games to list
                games.addAll(model.results)
                adapter.notifyDataSetChanged()
            }
        })
    }
}
