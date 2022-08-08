package com.justadroiddev.wordscleanarchapp.presentation.customview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.cardview.widget.CardView
import com.justadroiddev.wordscleanarchapp.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CustomCardSwiping @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CardView(context, attrs, defStyleAttr) {

    companion object {
        private const val START_X = 0f
        private const val START_Y = 0f
    }

    private val coroutineUi = CoroutineScope(Dispatchers.Main + Job())
    private val coroutineContext = coroutineUi.coroutineContext
    private var clickCount = 0
    private val listHearts = ArrayList<Heart>()
    private var heartBitmap: Bitmap? = null
    private var doubleClickListener: (() -> Unit)? = null
    private var doubleChecker = false

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        heartBitmap = makeHeart()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (heart in listHearts) {
            canvas?.drawBitmap(heart.getBitmap(), heart.getCords().first, heart.getCords().second, null)
        }
        if (listHearts.size >= 5){
            listHearts.remove(listHearts.first())
        }
    }

    fun clickIt(xHeart: Float, yHeart: Float) {
        if (clickCount == 0) {
            coroutineUi.launch {
                clickCount++
                delay(1000)
                clickCount = 0
            }
        } else {
            coroutineContext.cancelChildren()
            clickCount = 0
            addHeart(xHeart, yHeart)
            if (!doubleChecker){
                doubleClickListener?.invoke()
                doubleChecker = true
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                clickIt(event.x, event.y)
                true
            }
            else -> super.onTouchEvent(event)
        }


    }

    private fun addHeart(x: Float, y: Float){
        val newX = x - heartBitmap?.width?.toFloat()!! / 2
        val newY = y - heartBitmap?.height?.toFloat()!! / 2
        val heart = Heart(heartBitmap!!, newX, newY, this)
        listHearts.add(heart)
        CoroutineScope(Dispatchers.Main).launch{
            delay(500)
            listHearts.remove(listHearts.first())
        }
    }


    private fun makeHeart(): Bitmap {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.heart_tiny)
        return Bitmap.createScaledBitmap(bitmap, bitmap.width / 4, bitmap.height / 4, false)
    }

    fun setDoubleClickListener(actionListen: () -> Unit){
        doubleClickListener = actionListen
    }

}