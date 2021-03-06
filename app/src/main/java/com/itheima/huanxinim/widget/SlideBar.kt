package com.itheima.huanxinim.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.itheima.huanxinim.R
import kotlinx.android.synthetic.main.view_contact_item.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.sp

/**
 * author : yangjunjin
 * date : 2020/2/18 13:46
 */
class SlideBar(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var sectionHeight = 0.0f
    var textBaseline = 0f

    companion object {
        private val SELECTIONS = arrayOf(
            "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        )
    }

    var paint = Paint()

    init {
        paint.apply {
            color = resources.getColor(R.color.colorPrimary)
            textSize = sp(12).toFloat()
            //对齐居中
            textAlign = Paint.Align.CENTER
        }
    }

    //这个方法后就知道控件的宽高
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //计算每个字母的高度
        sectionHeight = h * 1.0f / SELECTIONS.size
        val fontMetrics = paint.fontMetrics
        //计算绘制文本的高度
        val textHeight = fontMetrics.descent - fontMetrics.ascent
        //计算准线
        textBaseline = sectionHeight / 2 + (textHeight / 2 - fontMetrics.descent)

        Log.e("textBaseline1", textBaseline.toString())
        Log.e("textBaseline2", (textHeight / 2).toString())
        Log.e("textBaseline3", fontMetrics.descent.toString())
        Log.e("textBaseline4", textHeight.toString())
    }

    //绘制所有的字母
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var x = width * 1.0f / 2 //绘制字符的开始位置
        var baseline = textBaseline
        SELECTIONS.forEach {
            canvas?.drawText(it, x, baseline, paint)
            baseline += sectionHeight
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                setBackgroundResource(R.drawable.bg_slide_bar)
                getTouchFirstLetter(event)
            }
            MotionEvent.ACTION_MOVE -> {
                getTouchFirstLetter(event)
            }
            MotionEvent.ACTION_UP -> {
                setBackgroundColor(Color.TRANSPARENT)
                context.runOnUiThread { onSectionChangeListener?.onSlideFinish() }
            }
        }
        return true
    }

    //找到点击的字母
    private fun getTouchFirstLetter(event: MotionEvent): Int {
        var index = (event.y / sectionHeight).toInt()
        if (index < 0) {
            index = 0
        } else if (index >= SELECTIONS.size) {
            index = SELECTIONS.size - 1
        }
        val firstLetter = SELECTIONS[index]
        Log.e("选中的字母==", firstLetter)
        context.runOnUiThread { onSectionChangeListener?.onSectionChange(firstLetter) }
        return index
    }

    var onSectionChangeListener: OnSectionChangeListener? = null

    //接口回调
    interface OnSectionChangeListener {
        fun onSectionChange(firstLetter: String)
        fun onSlideFinish()//完成的回调
    }
}