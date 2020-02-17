package com.itheima.huanxinim.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import cn.bmob.v3.helper.BmobNative.init
import com.itheima.huanxinim.R
import com.itheima.huanxinim.data.ContactListItem
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.view_contact_item.view.*

/**
 * author : yangjunjin
 * date : 2020/2/17 19:21
 */
class ContactListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    fun bindView(item: ContactListItem) {
        if (item.showFirstLetter) {
            firstLetter.visibility = View.VISIBLE
            firstLetter.text = item.firstLetter.toString()
        } else {
            firstLetter.visibility = View.GONE
        }
        name.text = item.userName
    }

    init {
        View.inflate(context, R.layout.view_contact_item, this)
    }
}