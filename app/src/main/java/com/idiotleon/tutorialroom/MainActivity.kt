package com.idiotleon.tutorialroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idiotleon.tutorialroom.adapter.WordListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvMain = findViewById<RecyclerView>(R.id.rv_main)
        rvMain.adapter = WordListAdapter(this)
        rvMain.layoutManager = LinearLayoutManager(this)
    }
}
