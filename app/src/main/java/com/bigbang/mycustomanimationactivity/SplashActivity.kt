package com.bigbang.mycustomanimationactivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val circleView: CircleView = CircleView(this)
        setContentView(circleView)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java).also {
                it.flags = (Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        }, 3000)
    }
}