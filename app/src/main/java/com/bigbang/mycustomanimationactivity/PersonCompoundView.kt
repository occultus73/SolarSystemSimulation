package com.bigbang.mycustomanimationactivity

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class PersonCompoundView(context: Context, attributeSet: AttributeSet) :
    CardView(context, attributeSet) {

    //Homework -> Implement setters
    var personName: String = ""
    var personInfoText: String = ""
    var buttonAText: String = ""
    var buttonBText: String = ""
    var imageDrawable: Int = 0

    private var personNameTextView: TextView
    private var personInfoTextView: TextView
    private var personImageView: ImageView
    private var buttonA: Button
    private var buttonB: Button

    init {

        val layoutInflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layoutInflater.inflate(R.layout.user_view_layout, this, true)

        val tArray = context.obtainStyledAttributes(attributeSet, R.styleable.PersonCompoundView)
        personName = tArray.getString(R.styleable.PersonCompoundView_personName) ?: ""
        personInfoText = tArray.getString(R.styleable.PersonCompoundView_personInfo) ?: ""
        buttonAText = tArray.getString(R.styleable.PersonCompoundView_buttonAText) ?: ""
        buttonBText = tArray.getString(R.styleable.PersonCompoundView_buttonBText) ?: ""
        imageDrawable = tArray.getResourceId(
            R.styleable.PersonCompoundView_personPic,
            R.drawable.ic_launcher_foreground
        )


        personNameTextView = this.findViewById(R.id.person_name_textview)
        personInfoTextView = this.findViewById(R.id.person_info_textview)
        personImageView = this.findViewById(R.id.view_imageview)
        buttonA = this.findViewById(R.id.first_button)
        buttonB = this.findViewById(R.id.second_button)

        setUpView()
    }


    private fun setUpView() {
        personNameTextView.text = personName
        personInfoTextView.text = personInfoText
        buttonA.text = buttonAText
        buttonB.text = buttonBText
        personImageView.setImageDrawable(context.getDrawable(imageDrawable))
    }
}