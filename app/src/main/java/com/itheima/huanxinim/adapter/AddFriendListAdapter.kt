package com.itheima.huanxinim.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itheima.huanxinim.data.AddFriendItem
import com.itheima.huanxinim.widget.AddFriendListItemView

/**
 * author : yangjunjin
 * date : 2020/2/17 19:33
 */
class AddFriendListAdapter(val context: Context, val addFriendItems: MutableList<AddFriendItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val addFriendItemView = holder.itemView as AddFriendListItemView
        if(addFriendItems.size>0)
        addFriendItemView.bindView(addFriendItems[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AddFriendListViewHolder(AddFriendListItemView(context))
    }

    override fun getItemCount(): Int =addFriendItems.size

    class AddFriendListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {}
}