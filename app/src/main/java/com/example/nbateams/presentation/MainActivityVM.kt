package com.example.nbateams.presentation

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nbateams.data.model.Team
import com.example.nbateams.data.model.Teams
import com.example.nbateams.data.service.TeamService
import com.example.nbateams.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityVM : ViewModel() {
    private val _teams: MutableLiveData<List<Team>> = MutableLiveData()

    val teams: LiveData<List<Team>>
        get() = _teams

    fun makeNetworkCall() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(TeamService::class.java)
        val call = service.getTeams()

        call.enqueue(object : Callback<Teams> {
            override fun onResponse(call: Call<Teams>, response: Response<Teams>) {
                if (response.isSuccessful) {
                    val teams = response.body()?.teams
                    teams?.let {
                        _teams.postValue(it) // LiveData'yı güncelle
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

}
