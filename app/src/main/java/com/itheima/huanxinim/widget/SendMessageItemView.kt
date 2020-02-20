package com.itheima.huanxinim.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import com.itheima.huanxinim.R
import kotlinx.android.synthetic.main.view_send_message_item.view.*
import java.util.*

/**
 * author : yangjunjin
 * date : 2020/2/19 16:04
 * 发送消息的View
 */
class SendMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.view_send_message_item, this)
    }

    fun bindView(emMessage: EMMessage, showTimestamp: Boolean) {
        updateTimestamp(emMessage,showTimestamp)
        updateMessage(emMessage)
        updateProgress(emMessage)
    }

    /**
     * 发送消息进度
     */
    private fun updateProgress(emMessage: EMMessage) {
        emMessage.status().let {
            when(it){
                //正在发送中
                EMMessage.Status.INPROGRESS->{
                    sendMessageProgress.visibility = View.VISIBLE
//                    val animationDrawable = sendMessageProgress.drawable as AnimationDrawable
//                    animationDrawable.start()
                }
                EMMessage.Status.SUCCESS->{
                    sendMessageProgress.visibility = View.GONE
                }

                EMMessage.Status.FAIL->{
                    sendMessageProgress.visibility = View.VISIBLE
                }
            }
        }
    }

    /**
     * 更新消息
     */
    private fun updateMessage(emMessage: EMMessage) {
        if (emMessage.type == EMMessage.Type.TXT) {
            sendMessage.text = (emMessage.body as EMTextMessageBody).message
        } else {
            sendMessage.text = context.getString(R.string.no_text_message)
        }
    }

    /**
     * 更新时间的
     */
    private fun updateTimestamp(emMessage: EMMessage, showTimestamp: Boolean) {
        if(showTimestamp){
            send_timestamp.visibility = View.VISIBLE
            send_timestamp.text = DateUtils.getTimestampString(Date(emMessage.msgTime))
        }else{
            send_timestamp.visibility = View.GONE
        }
    }
}