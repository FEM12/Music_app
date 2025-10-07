package com.example.music_app.model

data class Album(
    val id: Int,
    val artist_id: Int,
    val name: String,
    val publication_date: String,
    val discography: String
)