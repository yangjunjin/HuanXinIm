package com.itheima.huanxinim.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyphenate.chat.EMConversation
import com.itheima.huanxinim.ui.activity.ChatActivity
import com.itheima.huanxinim.widget.ConversationListItemView
import org.jetbrains.anko.startActivity

/**
 * author : yangjunjin
 * date : 2020/2/20 16:47
 */
class ConversationListAdapter(
    val context: Context, val conversations: MutableList<EMConversation>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return   ConversationListItemViewHolder(ConversationListItemView(context))
    }

    override fun getItemCount(): Int=conversations.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val conversationListItemView = holder.itemView as ConversationListItemView
        conversationListItemView.bindView(conversations[position])

        val userName = conversations[position].conversationId()
        conversationListItemView.setOnClickListener {
            context.startActivity<ChatActivity>("userName" to userName)
        }
    }

    class ConversationListItemViewHolder(itemView:View?):RecyclerView.ViewHolder(itemView!!)
}