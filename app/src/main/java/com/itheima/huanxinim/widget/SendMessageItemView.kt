package com.itheima.huanxinim.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.itheima.huanxinim.R

/**
 * author : yangjunjin
 * date : 2020/2/19 16:04
 * 发送消息的View
 */
class SendMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.view_send_message_item,this)
    }
}