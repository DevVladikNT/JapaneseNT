package com.vladiknt.japanesent

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun hiraganaButton(view: View?) {
        val intent = Intent(this, HiraganaActivity::class.java)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

    fun katakanaButton(view: View?) {
        val intent = Intent(this, KatakanaActivity::class.java)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

    fun kanjiButton(view: View?) {
        Toast.makeText(this, "This is unavailable now.", Toast.LENGTH_SHORT).show()
    }

    fun helpButton(view: View?) {
        val intent = Intent(this, HelpActivity::class.java)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }
}