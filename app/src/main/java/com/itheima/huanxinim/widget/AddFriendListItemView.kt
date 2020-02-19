package com.itheima.huanxinim.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMClient
import com.itheima.huanxinim.R
import com.itheima.huanxinim.adapter.EMCallBackAdapter
import com.itheima.huanxinim.data.AddFriendItem
import kotlinx.android.synthetic.main.view_add_friend_item.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast

/**
 * author : yangjunjin
 * date : 2020/2/17 19:21
 */
class AddFriendListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    fun bindView(friend: AddFriendItem) {
        userName.text = friend.userName
        time.text = friend.timesTamp
        if(friend.isAdded){
            addFriend.isEnabled = true
            addFriend.text = "添加"
            addFriend.textColor = resources.getColor(R.color.colorBlack)
        }else{
            addFriend.isEnabled = false
            addFriend.text = "已添加"
            addFriend.textColor = resources.getColor(R.color.colorGrayLight)
        }

        addFriend.setOnClickListener { addFriend(friend) }
    }

    //添加好友
    fun addFriend(friend: AddFriendItem){
        EMClient.getInstance().contactManager().aysncAddContact(friend.userName,null,object :EMCallBackAdapter(){
            override fun onSuccess() {
                super.onSuccess()
                context.runOnUiThread { toast("添加好友成功") }
            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                context.runOnUiThread { toast("添加好友失败") }
            }
        })
    }

    init {
        View.inflate(context, R.layout.view_add_friend_item, this)
    }
}