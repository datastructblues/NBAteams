package com.example.nbateams.data.service

import com.example.nbateams.data.model.Teams
import com.example.nbateams.utils.Constants.EXT
import retrofit2.Call
import retrofit2.http.GET

interface TeamService {

    @GET(EXT)
    fun getTeams(): Call<Teams>
}