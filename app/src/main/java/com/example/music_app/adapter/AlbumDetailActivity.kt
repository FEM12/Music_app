package com.example.music_app.adapter

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.music_app.R
import com.example.music_app.helper.DBHelper
import com.example.music_app.model.Album

class AlbumDetailActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        dbHelper = DBHelper(this)

        val albumId = intent.getIntExtra("album_id", -1)
        val album = dbHelper.getAlbumById(albumId)

        if (album != null) {
            findViewById<TextView>(R.id.lblAlbumName).text = album.name
            findViewById<TextView>(R.id.lblAlbumArtist).text = album.artist
            findViewById<TextView>(R.id.lblAlbumGenre).text = album.genre
            findViewById<TextView>(R.id.lblAlbumReleaseDate).text = album.releaseDate
        }
    }
}