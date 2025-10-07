package com.example.music_app

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.music_app.adapter.ArtistAdapter
import com.example.music_app.helper.DBHelper
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    lateinit var txtSearchArtists: EditText
    lateinit var btnSearchArtists: MaterialButton
    lateinit var gvArtists: GridView
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        init()
        dataLoad()

        btnSearchArtists.setOnClickListener {

            val text = txtSearchArtists.text.toString().trim()

            if(text.isEmpty()) Toast.makeText(this,"Error campo vacío", Toast.LENGTH_LONG).show()
            else dataFilterLoad(text)

        }

        txtSearchArtists.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    dataLoad()
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

    }

    private fun init() {

        txtSearchArtists = findViewById(R.id.txtSearchArtists)
        btnSearchArtists = findViewById(R.id.btnSearchArtists)
        gvArtists = findViewById(R.id.gvArtists)
        dbHelper = DBHelper(this)

    }

    private fun dataLoad() {

        val artistsList = dbHelper.getAllArtists().toMutableList()
        val adapter = ArtistAdapter(this,artistsList,dbHelper)
        gvArtists.adapter = adapter

    }

    private fun dataFilterLoad(name: String) {

        val artistList = dbHelper.getOneArtist(name).toMutableList()
        val adapter = ArtistAdapter(this,artistList,dbHelper)
        gvArtists.adapter = adapter

    }

}