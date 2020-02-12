package com.idiotleon.tutorialroom.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.idiotleon.tutorialroom.R

class NewWordActivity : AppCompatActivity() {

    private lateinit var etWord: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newword)

        etWord = findViewById(R.id.et_word)

        val btn: Button = findViewById(R.id.btn_save)
        btn.setOnClickListener {
            val replyIntent = Intent()
            if (etWord.text.isEmpty())
                setResult(Activity.RESULT_CANCELED, replyIntent)
            else {
                val word: String = etWord.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        val EXTRA_REPLY = "com.idiotleon.tutorialroom.REPLY"
    }
}