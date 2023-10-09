package com.example.nbateams.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.viewModels
import com.example.nbateams.R
import com.example.nbateams.data.model.Team
import com.example.nbateams.databinding.ActivityTeamDetailBinding

class TeamDetailActivity : AppCompatActivity() {

    private val viewModel: TeamDetailVM by viewModels()

    //initialize data binding

    var binding : ActivityTeamDetailBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    //    setContentView(R.layout.activity_team_detail)
        binding = ActivityTeamDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.viewModel = viewModel
        //binding viewmodel
        val team = intent.getSerializableExtra("team") as Team
        viewModel.setTeam(team)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}