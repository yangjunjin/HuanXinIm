package com.itheima.huanxinim.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import com.itheima.huanxinim.R
import kotlinx.android.synthetic.main.view_receive_message_item.view.*
import kotlinx.android.synthetic.main.view_send_message_item.view.*
import java.util.*

/**
 * author : yangjunjin
 * date : 2020/2/19 16:04
 * 接收消息的View
 */
class ReceiveMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.view_receive_message_item, this)
    }

    fun bindView(emMessage: EMMessage, showTimestamp: Boolean) {
        updateTimestamp(emMessage, showTimestamp)
        updateMessage(emMessage)
    }

    /**
     * 更新消息
     */
    private fun updateMessage(emMessage: EMMessage) {
        if (emMessage.type == EMMessage.Type.TXT) {
            receiveMessage.text = (emMessage.body as EMTextMessageBody).message
        } else {
            receiveMessage.text = context.getString(R.string.no_text_message)
        }
    }

    /**
     * 更新时间的
     */
    private fun updateTimestamp(emMessage: EMMessage, showTimestamp: Boolean) {
        if (showTimestamp) {
            receive_timestamp.visibility = View.VISIBLE
            receive_timestamp.text = DateUtils.getTimestampString(Date(emMessage.msgTime))
        } else {
            receive_timestamp.visibility = View.GONE
        }
    }
}