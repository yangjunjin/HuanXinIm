package com.itheima.huanxinim.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyphenate.chat.EMMessage
import com.itheima.huanxinim.widget.ReceiveMessageItemView
import com.itheima.huanxinim.widget.SendMessageItemView

/**
 * author : yangjunjin
 * date : 2020/2/19 16:47
 */
class MessageListAdapter(val context: Context, private val messages: List<EMMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_TYPE_SEND_MESSAGE = 0
        const val ITEM_TYPE_RECEIVE_MESSAGE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].direct() == EMMessage.Direct.SEND) {
            ITEM_TYPE_SEND_MESSAGE
        } else {
            ITEM_TYPE_RECEIVE_MESSAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE_SEND_MESSAGE) {
            SendMessageViewHolder(SendMessageItemView(context))
        } else ReceiveMessageViewHolder(ReceiveMessageItemView(context))
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_TYPE_SEND_MESSAGE) {
            val sendMessageItemView = holder.itemView as SendMessageItemView
            sendMessageItemView.bindView(messages?.get(position))
        } else {
            val receiveMessageItemView = holder.itemView as ReceiveMessageItemView
            receiveMessageItemView.bindView(messages?.get(position))
        }
    }

    //发送消息的Hodler
    class SendMessageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

    //接收消息
    class ReceiveMessageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}