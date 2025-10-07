package com.example.music_app

import android.os.Bundle
import android.widget.GridView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.music_app.adapter.AlbumAdapter
import com.example.music_app.helper.DBHelper

class ArtistDetailActivity: AppCompatActivity() {

    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_artist_detail)

        dbHelper = DBHelper(this)

        val artistId = intent.getIntExtra("artist_id", -1)
        val artist = dbHelper.getArtistById(artistId)

        if (artist != null) {
            findViewById<TextView>(R.id.lblArtistName).text = artist.name
            findViewById<TextView>(R.id.lblArtistStature).text = getString(R.string.stature_label, artist.stature.toString())
            findViewById<TextView>(R.id.lblArtistNationality).text = getString(R.string.nationality_label, artist.nationality)
            findViewById<TextView>(R.id.lblArtistBirthdate).text = getString(R.string.birthdate_label, artist.birthdate)

            val albumsList = dbHelper.getAllAlbums(artistId).toMutableList()
            val adapter = AlbumAdapter(this,albumsList,dbHelper)
            findViewById<GridView>(R.id.gvAlbums).adapter = adapter
        }



    }
}