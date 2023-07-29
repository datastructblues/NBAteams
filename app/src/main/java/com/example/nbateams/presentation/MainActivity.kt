package com.example.nbateams.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nbateams.R
import com.example.nbateams.data.model.Team
import com.example.nbateams.data.model.Teams
import com.example.nbateams.data.service.TeamService
import com.example.nbateams.utils.Constants.BASE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //call makeNetowrkCall() function
        val scope = CoroutineScope(Dispatchers.Main)
        makeNetworkCall()
    }

    private fun makeNetworkCall() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(TeamService::class.java)
        val call = service.getTeams()

        call.enqueue(object : Callback<Teams> {
            override fun onResponse(call: Call<Teams>, response: Response<Teams>) {
                if (response.isSuccessful) {
                    val teams = response.body()
                    teams?.let {
                        showDataInRecyclerView(teams.teams)
                    }
                } else {
                    Log.e("API Error", "Response not successful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Teams>, t: Throwable) {
                Log.e("API Error", "Network error or request failure", t)
            }
        })
    }

    private fun showDataInRecyclerView(teams: List<Team>) {
        val recyclerView: RecyclerView = findViewById(R.id.listView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TeamAdapter(teams)
    }
}