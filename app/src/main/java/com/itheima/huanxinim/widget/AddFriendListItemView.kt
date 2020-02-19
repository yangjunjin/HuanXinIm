package com.itheima.huanxinim.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import cn.bmob.v3.helper.BmobNative.init
import com.itheima.huanxinim.R
import com.itheima.huanxinim.data.AddFriendItem
import kotlinx.android.synthetic.main.view_add_friend_item.view.*

/**
 * author : yangjunjin
 * date : 2020/2/17 19:21
 */
class AddFriendListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    fun bindView(item: AddFriendItem) {
        userName.text = item.userName
        time.text = item.timesTamp
        if(item.isAdded){
            addFriend.isEnabled = true
            addFriend.text = "添加"
        }else{
            addFriend.isEnabled = false
            addFriend.text = "已添加"
        }
    }

    init {
        View.inflate(context, R.layout.view_add_friend_item, this)
    }
}