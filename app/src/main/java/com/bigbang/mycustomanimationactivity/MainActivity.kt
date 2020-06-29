package com.bigbang.mycustomanimationactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val list =  mutableListOf<String>("Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello")

        var rvAdapter = RVAdapter(list)

        rview.adapter = rvAdapter

//        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_anim)
//        val animation2 = AnimationUtils.loadAnimation(this, R.anim.slide_in_anim_from_left)
//        val animation3 = AnimationUtils.loadAnimation(this, R.anim.scale_animation)
//        person1.startAnimation(animation)
//        person2.startAnimation(animation2)
//
//        button.setOnClickListener { view ->
//            person1.startAnimation(animation)
//            person2.startAnimation(animation2)
//            view.startAnimation(animation3)
//        }




//
    }
}