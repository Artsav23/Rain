package com.example.rain

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*

class RainObject(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {

    private var posList = mutableListOf<Position>()
    private val color = listOf(Color.WHITE, Color.RED, Color.BLUE, Color.GREEN, Color.GRAY, Color.YELLOW)
    var  kindFigure: KindFigure = KindFigure.Square
    var positions: Int = 0
    var mixColor = false
    var sizeFigure = 20f
    var direction = 0
    var timer: Timer? = null

    private val rainPaint = Paint().apply {
        color = Color.WHITE
        strokeWidth = 20f
        style = Paint.Style.STROKE
    }

    fun start() {
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                invalidate()
            }
        }, 0, 50)
    }

    fun stop() {
        timer?.cancel()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        positions = width / 40
        createRain()
        posList.forEach {
            changeColor()
            rainPaint.strokeWidth = sizeFigure
            draw(canvas, it.xPos.toFloat(), it.yPos.toFloat())
            it.yPos += 10
            it.xPos += direction
        }
        checkPoint()
    }

    private fun changeColor() {
        if (mixColor) randomColor()

        else rainPaint.color = Color.WHITE
    }

    private fun draw(canvas: Canvas?, x: Float, y:Float) {
        when (kindFigure) {
            KindFigure.Square -> canvas?.drawPoint(x, y, rainPaint)
            KindFigure.Circle -> canvas?.drawCircle(x, y, sizeFigure/3,rainPaint)
            KindFigure.Line -> canvas?.drawLine(x, y - sizeFigure * 5, x, y, rainPaint)
        }
    }

    private fun checkPoint() {
        for (i in posList.size-1 downTo 0) {
            if (posList[i].yPos >= height) {
                posList.removeAt(i)
            }
            if (posList[i].yPos * 4 >= height && !posList[i].douplicate) {
                posList[i].douplicate = true
                posList.add(Position((1..40).random() * positions,height / (2..15).random())  )
            }
        }
    }

    private fun createRain() {
        if (posList.isEmpty()) {
            for (i in 1..40) {
                val yPos = height / (2..20).random()
                posList.add(Position( i * positions, yPos))
            }
        }
    }

    private fun randomColor() {
        rainPaint.color = color.random()
    }
}