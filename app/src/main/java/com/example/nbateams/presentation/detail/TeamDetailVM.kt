package com.example.nbateams.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nbateams.data.model.Team

class TeamDetailVM: ViewModel() {
    var team : MutableLiveData<Team> = MutableLiveData()


    fun setTeam(team: Team) {
        this.team.value = team
    }
}