package com.example.music_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.music_app.R
import com.example.music_app.helper.DBHelper
import com.example.music_app.model.Artist

class ArtistAdapter(
    private val context: Context,
    private val items: MutableList<Artist>,
    private val dbHelper: DBHelper
): ArrayAdapter<Artist>(context,0,items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val itemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.artist_list_element,parent,false)


        val llArtistCard = itemView.findViewById<LinearLayout>(R.id.llArtistCard)
        val lblArtistName = itemView.findViewById<TextView>(R.id.lblArtistName)

        val artist = items[position]

        lblArtistName.text = artist.name.replace(" ","\n")
        llArtistCard.setOnClickListener {}


        return itemView

    }

}