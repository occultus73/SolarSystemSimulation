package com.bigbang.mycustomanimationactivity

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView

class CompoundView(context: Context, attributeSet: AttributeSet) :
    CardView(context, attributeSet) {

    //Homework -> Implement setters
    var title: String = ""
    var speedInfo: String = ""
    var buttonAText: String = ""
    var buttonBText: String = ""

    private var titleTextView: TextView
    private var speedInfoTextView: TextView
    private var buttonFaster: Button
    private var buttonSlower: Button
    private var solarSystem: CircleView

    init {

        val layoutInflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layoutInflater.inflate(R.layout.user_view_layout, this, true)

        val tArray = context.obtainStyledAttributes(attributeSet, R.styleable.CompoundView)
        title = tArray.getString(R.styleable.CompoundView_personName) ?: ""
        speedInfo = tArray.getString(R.styleable.CompoundView_personInfo) ?: ""
        buttonAText = tArray.getString(R.styleable.CompoundView_buttonAText) ?: ""
        buttonBText = tArray.getString(R.styleable.CompoundView_buttonBText) ?: ""


        titleTextView = this.findViewById(R.id.person_name_textview)
        speedInfoTextView = this.findViewById(R.id.person_info_textview)
        buttonFaster = this.findViewById(R.id.first_button)
        buttonSlower = this.findViewById(R.id.second_button)
        solarSystem = this.findViewById(R.id.solarSystem)

        setUpView()
    }


    private fun setUpView() {
        titleTextView.text = title
        speedInfoTextView.text = speedInfo
        buttonFaster.text = buttonAText
        buttonSlower.text = buttonBText

        buttonFaster.setOnClickListener {
            solarSystem.speed += 0.001f
        }
        buttonSlower.setOnClickListener {
            solarSystem.speed -= 0.001f
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        speedInfoTextView.text = "%.2f Yrs/Min".format(((solarSystem.speed * 1000f) / 2.75f))
        invalidate()
    }
}