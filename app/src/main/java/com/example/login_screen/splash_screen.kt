package com.example.login_screen

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.VideoView
import java.io.File

class ss : AppCompatActivity() {
    private lateinit var vox :VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        vox= findViewById<VideoView>(R.id.bv)
        val vp = "android.resource://" + packageName +"/"+R.raw.vvsd
        vox.setVideoURI(Uri.parse(vp))
        vox.start()
        vox.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
        }



        // Create a handler to delay the start of the main activity
        val handler = Handler()
        handler.postDelayed({
            // Start the main activity
            startActivity(Intent(this, sigin::class.java))

            // Finish the splash screen activity
            finish()
        }, 3000) // 3000 milliseconds = 3 seconds
    }
    override fun onResume() {
        super.onResume()
        vox.start()
    }

    override fun onRestart() {
        super.onRestart()
        vox.start()
    }

    override fun onPause() {
        super.onPause()
        vox.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        vox.stopPlayback()
    }


}
