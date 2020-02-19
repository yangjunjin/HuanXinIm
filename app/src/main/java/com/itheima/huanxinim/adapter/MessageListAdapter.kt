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
class MessageListAdapter(val context:Context,val messages:List<EMMessage>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        val ITEM_TYPE_SEND_MESSAGE=0
        val ITEM_TYPE_RECEIVE_MESSAGE=1
    }

    override fun getItemViewType(position: Int): Int {
        if(messages[position].direct()==EMMessage.Direct.SEND){
            return ITEM_TYPE_SEND_MESSAGE
        }else{
            return ITEM_TYPE_RECEIVE_MESSAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType== ITEM_TYPE_SEND_MESSAGE) {
            SendMessageViewHolder(SendMessageItemView(context))
        }else ReceiveMessageViewHolder(ReceiveMessageItemView(context))
    }

    override fun getItemCount(): Int=messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }
    //发送消息的Hodler
    class SendMessageViewHolder(itemView:View?):RecyclerView.ViewHolder(itemView!!){}
    //接收消息
    class ReceiveMessageViewHolder(itemView:View?):RecyclerView.ViewHolder(itemView!!){}
}