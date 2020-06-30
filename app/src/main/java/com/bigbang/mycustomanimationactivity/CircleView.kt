package com.bigbang.mycustomanimationactivity

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.lang.Math.pow
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

class CircleView(context: Context, attributeSet: AttributeSet? = null) :
    View(context, attributeSet) {//Extend the View/[SpecificView]Class

    // Extracted from https://www.khanacademy.org/computer-programming/the-solar-system/957249502
    // Created by: Jessica Liu (Updated 8 years ago)
    // Simulating the solar system
    // Information from http://www.enchantedlearning.com/subjects/astronomy/planets/

    //planet colors
    private val colorMercury = Paint().also{ it.color = Color.rgb(219, 217, 212) }
    private val colorVenus = Paint().also{ it.color = Color.rgb(205, 212, 106) }
    private val colorEarth = Paint().also{ it.color = Color.rgb(36, 136, 212) }
    private val colorMars = Paint().also{ it.color = Color.rgb(255,0,0) }
    private val colorJupiter = Paint().also{ it.color = Color.rgb(173, 107, 37) }
    private val colorSaturn = Paint().also{ it.color = Color.rgb(163, 131, 77) }
    private val colorUranus = Paint().also{ it.color = Color.rgb(113, 222, 230) }
    private val colorNeptune = Paint().also{ it.color = Color.rgb(206, 89, 235) }
    private val colorPluto = Paint().also{ it.color = Color.rgb(240, 57, 130) }
    private val colorSun = Paint().also{ it.color = Color.rgb(255, 255, 0) }

    private val colorText = Paint().also{ it.color = Color.WHITE }
    private val colorOrbit = Paint().also{
        it.color = Color.GRAY
        it.style = Paint.Style.STROKE
    }

    //speed. 1/year
    private val mercurySpeed = 365.26f/87.96f
    private val venusSpeed =  365.26f/224.68f
    private val earthSpeed = 1.0f
    private val marsSpeed = 365.26f/686.98f
    private val jupiterSpeed = 1f/11.862f
    private val saturnSpeed = 1f/29.456f
    private val uranusSpeed = 1f/84.07f
    private val neptuneSpeed = 1f/164.81f
    private val plutoSpeed = 1f/247.7f

    //size
    private val mercurySize = 5.0f
    private val venusSize = 10.0f
    private val earthSize = 10.0f
    private val marsSize = 7.0f
    private val jupiterSize = 20.0f
    private val saturnSize = 15.0f
    private val uranusSize = 10.0f
    private val neptuneSize = 10.0f
    private val plutoSize = 5.0f

    // Orbit
    private val mercuryOrbit = 1.0f
    private val venusOrbit = 2.0f
    private val earthOrbit = 3.0f
    private val marsOrbit = 4.0f
    private val jupiterOrbit = 5.0f
    private val saturnOrbit = 6.0f
    private val uranusOrbit = 7.0f
    private val neptuneOrbit = 8.0f
    private val plutoOrbit = 9.0f

    // These control the shape of the orbits - must be set relative to View width & height
    private val cX by lazy { width / 2.0f }
    private val cY by lazy { height / 2.0f }

    private val baseSize = 100.0f
    private val incrX = 30.0f
    private val incrY = 20.0f

    // model variables
    var speed = 0.1f
    var currentTime = 0.0f
    var animate = true

    private fun Canvas.drawEllipse(cX: Float, cY: Float, width: Float, height: Float, paint: Paint){
        this.drawOval(cX - width/2, cY - height/2, cX + width/2, cY + height/2, paint)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        fun drawPlanet(time: Float, speed: Float, orbit: Float, size: Float, planetColor: Paint) {
            // Draw the planet on a particular orbit
            val degree = (time * speed)  % 360

            // Calculate the position of the planet
            val radiusX = (baseSize + incrX * orbit.pow(1.1f)) / 2.0f
            val radiusY = (baseSize + incrY * orbit.pow(1.1f)) / 2.0f
            val positionX = radiusX * cos(degree) + cX
            val positionY = radiusY * sin(degree) + cY

            // Draw the planet
            canvas.drawEllipse(positionX, positionY, size, size, planetColor)

            // Saturn ring
            if (orbit == saturnOrbit) {
                canvas.drawEllipse(positionX, positionY, 1.5f*size, 0.25f*size, colorSaturn)
            }
            // Pluto may not be a planet?
            if (orbit == plutoOrbit) {
                canvas.drawText("?", positionX, positionY, colorText)
            }
        }

        fun drawSolarSystem (time: Float) {

            // Sun
            canvas.drawEllipse(cX, cY, baseSize, baseSize, colorSun)

            // Draw the orbits first
            for (i in 1..9) {
                val diameterX = baseSize + incrX * i.toFloat().pow(1.1f)
                val diameterY = baseSize + incrY * i.toFloat().pow(1.1f)
                canvas.drawEllipse(cX, cY, diameterX, diameterY, colorOrbit)
            }

            // Mercury
            drawPlanet(time, mercurySpeed, mercuryOrbit, mercurySize, colorMercury)
            // Venus
            drawPlanet(time, venusSpeed, venusOrbit, venusSize, colorVenus)
            // Earth
            drawPlanet(time, earthSpeed, earthOrbit, earthSize, colorEarth)
            // Mars
            drawPlanet(time, marsSpeed, marsOrbit, marsSize, colorMars)
            // Jupiter
            drawPlanet(time, jupiterSpeed, jupiterOrbit, jupiterSize, colorJupiter)
            // Saturn
            drawPlanet(time, saturnSpeed, saturnOrbit, saturnSize, colorSaturn)
            // Uranus
            drawPlanet(time, uranusSpeed, uranusOrbit, uranusSize, colorUranus)
            // Neptune
            drawPlanet(time, neptuneSpeed, neptuneOrbit, neptuneSize, colorNeptune)
            // Pluto
            drawPlanet(time, plutoSpeed, plutoOrbit, plutoSize, colorPluto)
        }

        if (animate) {
            drawSolarSystem(currentTime)
            currentTime += speed
        }

        invalidate()
    }
}