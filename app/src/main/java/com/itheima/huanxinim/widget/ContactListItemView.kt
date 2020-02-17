package com.itheima.huanxinim.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.itheima.huanxinim.R

/**
 * author : yangjunjin
 * date : 2020/2/17 19:21
 */
class ContactListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.view_contact_item,this)
    }
}