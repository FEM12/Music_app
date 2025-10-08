package com.example.music_app

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.music_app.helper.DBHelper

class SongDetailActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_song_detail)

        dbHelper = DBHelper(this)

        val songs = intent.getIntExtra("song_id",-1)
        val song = dbHelper.getSongById(songs)

        if(song != null) {

            findViewById<TextView>(R.id.lblSongTitle).text = song.name
            findViewById<TextView>(R.id.lblSongReleaseDate).text = song.publication_date
            findViewById<TextView>(R.id.lblSongDuration).text = song.duration
            findViewById<TextView>(R.id.lblSongGenre).text = song.genre

        }

    }
}