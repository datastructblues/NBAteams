package com.example.nbateams.presentation

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.nbateams.data.model.Team

class MainActivityVM :ViewModel(){

    var teamColor = ObservableField<String>()
    var teams: ObservableField<List<Team>> = ObservableField()
}