package com.example.music_app.adapter

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.music_app.R
import com.example.music_app.helper.DBHelper
import com.example.music_app.model.Artist

class ArtistDetailActivity: AppCompatActivity() {

    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_detail)

        dbHelper = DBHelper(this)

        val artistId = intent.getIntExtra("artist_id", -1)
        val artist = dbHelper.getArtistById(artistId)

        if (artist != null) {
            findViewById<TextView>(R.id.lblArtistName).text = artist.name
            findViewById<TextView>(R.id.lblArtistStature).text = getString(R.string.stature_label, artist.stature.toString())
            findViewById<TextView>(R.id.lblArtistNationality).text = getString(R.string.nationality_label, artist.nationality)
            findViewById<TextView>(R.id.lblArtistBirthdate).text = getString(R.string.birthdate_label, artist.birthdate)
        }
    }
}