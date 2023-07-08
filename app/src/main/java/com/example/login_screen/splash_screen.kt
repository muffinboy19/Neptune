package com.example.login_screen

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.VideoView
import java.io.File

class ss : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Create a handler to delay the start of the main activity
        val handler = Handler()
        handler.postDelayed({
            // Start the main activity
            startActivity(Intent(this, sigin::class.java))

            // Finish the splash screen activity
            finish()
        }, 3000) // 3000 milliseconds = 3 seconds
    }
}
