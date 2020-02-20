package com.itheima.huanxinim.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itheima.huanxinim.data.db.Contact
import com.itheima.huanxinim.widget.ConversationListItemView

/**
 * author : yangjunjin
 * date : 2020/2/20 16:47
 */
class ConversationListAdapter(val context:Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return   ConversationListItemViewHolder(ConversationListItemView(context))
    }

    override fun getItemCount(): Int=30

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    class ConversationListItemViewHolder(itemView:View?):RecyclerView.ViewHolder(itemView!!)
}