package com.example.nbateams.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nbateams.R
import com.example.nbateams.data.model.Team

class MainActivity : AppCompatActivity() {

    //create viewmodel by viewmodels
    private val viewModel: MainActivityVM by viewModels()


//make databinding to get things done.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.makeNetworkCall()
        observe()
        //create a list of teams
   //     var list = listOf<Team>(Team("name", listOf("red", "blue"), "logo", "championship", "established", "franchise_star", listOf("retired"), "mascot", "arena", "gleague_team"))
    }



    private fun showDataInRecyclerView(teams: List<Team>) {
        val recyclerView: RecyclerView = findViewById(R.id.listView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TeamAdapter(teams){
            onTeamClicked(it)
        }
    }

    private fun observe(){
        viewModel.teams.observe(this) { teams ->
            teams?.let {
                // LiveData'dan gelen veriyi kullanın
                showDataInRecyclerView(teams)
                /*
                for (team in teams) {
                    println(team.name)
                }
                 */
            }
        }
    }

    private fun onTeamClicked(team: Team) {
        val intent = Intent(this, TeamDetailActivity::class.java)
        intent.putExtra("team", team)
        startActivity(intent)
    }
}