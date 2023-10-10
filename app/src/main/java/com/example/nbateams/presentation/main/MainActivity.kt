package com.example.nbateams.presentation.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nbateams.R
import com.example.nbateams.data.model.Team
import com.google.android.material.bottomsheet.BottomSheetDialog
import de.hdodenhof.circleimageview.CircleImageView

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
        val dialog = CustomBottomSheetDialog(this, team)
        val view = layoutInflater.inflate(R.layout.bottomsheet, null)


        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(view)
        dialog.show()
        /*
        val intent = Intent(this, TeamDetailActivity::class.java)
        intent.putExtra("team", team)
        startActivity(intent)

         */
    }
}

class CustomBottomSheetDialog(context: Context, private val team: Team) : BottomSheetDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = layoutInflater.inflate(R.layout.bottomsheet, null)

        val teamName = view.findViewById<TextView>(R.id.team_name)
        teamName.text = team.name

        val teamLogo = view.findViewById<CircleImageView>(R.id.team_logo)
        Glide.with(context).load(team.logo).into(teamLogo)

        val established = view.findViewById<TextView>(R.id.established)
        established.text = team.established

        val arena = view.findViewById<TextView>(R.id.arena)
        arena.text = team.arena

        val championship = view.findViewById<TextView>(R.id.championship)
        championship.text = team.championship

        val franchiseStar = view.findViewById<TextView>(R.id.franchise_star)
        franchiseStar.text = team.franchise_star

        val retired = view.findViewById<TextView>(R.id.retired_numbers)
        retired.text = team.retired.toString()

        val mascot = view.findViewById<TextView>(R.id.mascot)
        mascot.text = team.mascot

        val gLeagueTeam = view.findViewById<TextView>(R.id.g_league)
        gLeagueTeam.text = team.gleague_team



        setContentView(view)
    }
}