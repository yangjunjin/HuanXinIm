package com.itheima.huanxinim.widget

import android.content.Context
import android.database.DatabaseUtils
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import cn.bmob.v3.helper.BmobNative.init
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import com.itheima.huanxinim.R
import kotlinx.android.synthetic.main.view_conversation_item.view.*
import java.util.*
import kotlin.math.sign

/**
 * author : yangjunjin
 * date : 2020/2/20 16:21
 * 会话列表的item
 */
class ConversationListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.view_conversation_item, this)
    }

    fun bindView(emConversation: EMConversation) {
        userName.text = emConversation.conversationId()
        if(emConversation.lastMessage.type == EMMessage.Type.TXT){
            val body = emConversation.lastMessage.body as EMTextMessageBody
            lastMessage.text = body.message
        }else{
            lastMessage.text = "不是文本消息"
        }

        val timestampString = DateUtils.getTimestampString(Date(emConversation.lastMessage.msgTime))
        timestamp.text = timestampString
        if(emConversation.unreadMsgCount>0){
            unreadCount.visibility = View.VISIBLE
            unreadCount.text = emConversation.unreadMsgCount.toString()
        }else{
            unreadCount.visibility = View.GONE
        }
    }
}