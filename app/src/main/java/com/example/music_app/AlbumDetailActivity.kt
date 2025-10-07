package com.example.music_app

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.music_app.adapter.AlbumAdapter
import com.example.music_app.adapter.SongAdapter
import com.example.music_app.helper.DBHelper

class AlbumDetailActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_album_detail)

        dbHelper = DBHelper(this)

        val albumId = intent.getIntExtra("album_id", -1)
        val album = dbHelper.getAlbumById(albumId)

        if(album != null) {

            findViewById<TextView>(R.id.lblAlbumName).text = album.name
            findViewById<TextView>(R.id.lblPublicationDate).text = getString(R.string.album_publication_date, album.publication_date)
            findViewById<TextView>(R.id.lblDiscography).text = getString(R.string.album_discography, album.discography)

            val songList = dbHelper.getAllSongs(albumId).toMutableList()
            val adapter = SongAdapter(this,songList,dbHelper)
            findViewById<GridView>(R.id.gvSongs).adapter = adapter

        }

    }
}