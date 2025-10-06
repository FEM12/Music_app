package com.example.music_app.adapter

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.music_app.R
import com.example.music_app.helper.DBHelper
import com.example.music_app.model.Song

class SongDetailActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        dbHelper = DBHelper(this)

        val songId = intent.getIntExtra("song_id", -1)
        val song = dbHelper.getSongById(songId)

        if (song != null) {
            findViewById<TextView>(R.id.lblSongTitle).text = song.title
            findViewById<TextView>(R.id.lblSongArtist).text = song.artist
            findViewById<TextView>(R.id.lblSongAlbum).text = song.album
            findViewById<TextView>(R.id.lblSongGenre).text = song.genre
            findViewById<TextView>(R.id.lblSongDuration).text = song.duration
        }
    }
}