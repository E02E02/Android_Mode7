package com.example.android.mode7

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.view.MotionEventCompat

@Suppress("DEPRECATION")
class ViewPort @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), View.OnTouchListener {

    //Initialize data
    //private val mDataset = Datasource().loadMaps()

    private var viewPortRight = resources.getDimension(R.dimen.viewPortRectRight)
    private var viewPortBottom = resources.getDimension(R.dimen.viewPortRectBottom)
    //private var viewPortTop = resources.getDimension(R.dimen.viewPortRectTop)
    //private var viewPortLeft = resources.getDimension(R.dimen.viewPortRectLeft)

    //frame around the viewport
    private lateinit var frameViewPort: Rect

    private val paint = Paint().apply {
        // Smooth out edges of what is drawn without affecting shape.
        isAntiAlias = true
        strokeWidth = resources.getDimension(R.dimen.strokeWidth)
        textSize = resources.getDimension(R.dimen.textSize)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawViewPort(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        frameViewPort = Rect(0, 0, viewPortRight.toInt(), viewPortBottom.toInt())
    }

    private fun drawViewPort(canvas: Canvas) {

        canvas.translate(40.0F, 0.0F)
        canvas.save()

        canvas.drawColor(Color.TRANSPARENT)
        canvas.clipRect(frameViewPort)

        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        canvas.drawRect(frameViewPort, paint)

        canvas.restore()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        //translation, skewing and rotation of ViewPort
        Log.d("debug", "test")

        return when (MotionEventCompat.getActionMasked(event)) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("DEBUG_TAG", "Action was DOWN")
                true
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d("DEBUG_TAG", "Action was MOVE")
                true
            }
            MotionEvent.ACTION_UP -> {
                Log.d("DEBUG_TAG", "Action was UP")
                true
            }
            MotionEvent.ACTION_CANCEL -> {
                Log.d("DEBUG_TAG", "Action was CANCEL")
                true
            }
            MotionEvent.ACTION_OUTSIDE -> {
                Log.d("DEBUG_TAG", "Movement occurred outside bounds of current screen element")
                true
            }
            else -> super.onTouchEvent(event)
        }
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true
        return true
    }

}