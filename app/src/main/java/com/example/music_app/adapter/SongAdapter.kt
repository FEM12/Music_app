package com.example.music_app.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.music_app.AlbumDetailActivity
import com.example.music_app.R
import com.example.music_app.SongDetailActivity
import com.example.music_app.helper.DBHelper
import com.example.music_app.model.Album
import com.example.music_app.model.Song

class SongAdapter(
    private val context: Context,
    private val items: MutableList<Song>,
    private val dbHelper: DBHelper
): ArrayAdapter<Song>(context,0,items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val itemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.artist_list_element,parent,false)


        val llArtistCard = itemView.findViewById<LinearLayout>(R.id.llArtistCard)
        val lblArtistName = itemView.findViewById<TextView>(R.id.lblArtistName)

        val song = items[position]

        lblArtistName.text = song.name.replace(" ","\n")
        llArtistCard.setOnClickListener {

            val intent = Intent(context, SongDetailActivity::class.java)
            intent.putExtra("song_id", song.id)
            context.startActivity(intent)

        }


        return itemView

    }

}