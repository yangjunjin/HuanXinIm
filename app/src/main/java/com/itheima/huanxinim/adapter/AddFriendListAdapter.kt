package com.itheima.huanxinim.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itheima.huanxinim.data.ContactListItem
import com.itheima.huanxinim.widget.AddFriendListItemView

/**
 * author : yangjunjin
 * date : 2020/2/17 19:33
 */
class AddFriendListAdapter(val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AddFriendListViewHolder(AddFriendListItemView(context))
    }

    override fun getItemCount(): Int = 30

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    class AddFriendListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {}
}