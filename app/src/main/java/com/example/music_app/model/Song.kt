package com.example.music_app.model

 data class Song(
    val id: Int,
    val album_id: Int,
    val name: String,
    val publication_date: String,
    val duration: String,
    val genre: String,
)