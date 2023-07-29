package com.example.nbateams.data.model

data class Team(
    val name: String,
    val colors: List<String>,
    val logo: String,
    val championship: String,
    val established: String,
    val franchise_star: String,
    val retired: List<Any>, // "retired" could contain numbers and strings. therefore we use Any.
    val mascot: String,
    val arena: String,
    val gleague_team: String
)

data class Teams(
    val teams: List<Team>
)