package com.bigbang.mycustomanimationactivity

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class CircleView(context: Context, attributeSet: AttributeSet?) :
    View(context, attributeSet) {//Extend the View/[SpecificView]Class

    constructor(context: Context) : this(context, null)

    private var circleColor: Int = Color.GRAY

    private var animate = true

    private var paintObject: Paint

    private var viewText = "Hello"
    private var angle: Float = 0f

    private var secondX = 0f
    private var secondY = 0f

    init {
        paintObject = Paint()
        paintObject.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) { //Where I will draw my circle
        super.onDraw(canvas)

        val drawableWidth = width / 2
        val drawableHeight = height / 2

        val circleRadius: Float = (Math.min(drawableWidth, drawableHeight) / 2).toFloat()
        val cY = width / 2
        val cX = height / 2

        paintObject.color = circleColor
        canvas.drawCircle(cX.toFloat(), cY.toFloat(), circleRadius, paintObject)

        if (animate) {
            angle += 0.10f

            if (angle >= 360)
                angle = 0f


            secondX = circleRadius / 2 * sin(angle) + cX
            secondY = circleRadius / 2 * cos(angle) + cY

        }
        paintObject.color = Color.BLACK
        canvas.drawCircle(secondX, secondY, circleRadius / 2, paintObject)

        canvas.drawText(viewText, secondX, secondY, paintObject.also {
            it.color = Color.WHITE
        })

        invalidate()
    }

    fun setAnimation() {
        animate = !animate
    }

    fun setText(viewText: String){
        this.viewText = viewText
    }


}