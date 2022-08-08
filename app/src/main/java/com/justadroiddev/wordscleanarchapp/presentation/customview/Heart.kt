package com.justadroiddev.wordscleanarchapp.presentation.customview

import android.graphics.Bitmap
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class Heart(
    private val bitmap: Bitmap,
    private var x: Float,
    private var y: Float,
    private val view: View
) {
    fun getBitmap() = bitmap
    fun getCords(): Pair<Float, Float> = Pair(x, y)
    private val Y_START = y
    private var movingEnd = false

    fun moveUp() = CoroutineScope(Dispatchers.Main).launch{
        while (y >= Y_START-200f){
            y -= 10f
            delay(25)
            view.invalidate()
        }
        movingEnd = true
    }

    fun isAnimationEnd() = movingEnd

    init {
        moveUp()
    }

}