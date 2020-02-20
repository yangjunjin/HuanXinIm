package com.itheima.huanxinim.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import cn.bmob.v3.helper.BmobNative.init
import com.itheima.huanxinim.R

/**
 * author : yangjunjin
 * date : 2020/2/20 16:21
 */
class ConversationListItemView(context: Context?=null, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.view_conversation_item,this)
    }
}