package com.example.nbateams.presentation.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nbateams.R
import com.example.nbateams.data.model.Team
import de.hdodenhof.circleimageview.CircleImageView

class TeamAdapter(
    private var teams: List<Team>,
    private val onItemClick: (Team) -> Unit
    ) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    // Diğer metotlar ve işlemler...


    inner  class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val teamName = itemView.findViewById<TextView>(R.id.team_name)
        val teamLogo = itemView.findViewById<CircleImageView>(R.id.team_logo)
        // Diğer görünümler burada tanımlanabilir.
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val team = teams[position]
                    onItemClick(team)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.team_item, parent, false)
        return TeamViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val currentTeam = teams[position]
        holder.teamName.text = currentTeam.name
        //load image with glide
        Glide.with(holder.itemView.context).load(currentTeam.logo).into(holder.teamLogo)
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItemValues(){
        this.teams = teams
        notifyDataSetChanged()
    }
}
