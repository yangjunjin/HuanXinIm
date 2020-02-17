package com.itheima.huanxinim.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itheima.huanxinim.R
import com.itheima.huanxinim.data.ContactListItem
import com.itheima.huanxinim.ui.activity.ChatActivity
import com.itheima.huanxinim.widget.ContactListItemView
import org.jetbrains.anko.AlertBuilder
import org.jetbrains.anko.startActivity

/**
 * author : yangjunjin
 * date : 2020/2/17 19:33
 */
class ContactListAdapter(val context: Context, private val contactListItems: MutableList<ContactListItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactListItemViewHolder(ContactListItemView(context))
    }

    override fun getItemCount(): Int = contactListItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contactListItemView = holder.itemView as ContactListItemView
        contactListItemView.bindView(contactListItems[position])
        val userName = contactListItems[position].userName
        contactListItemView.setOnClickListener { context.startActivity<ChatActivity>("userName" to userName) }
        contactListItemView.setOnLongClickListener {
            val message = String.format(context.getString(R.string.delete_friend), userName)
            AlertDialog.Builder(context).setTitle(context.getString(R.string.delete_friend_tip)).setMessage(message)
                .setNegativeButton(context.getString(R.string.cancel), null)
                .setPositiveButton(
                    context.getString(R.string.ok),
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        deleteFriend(userName)
                    }).show()
            true
        }
    }

    //删除好友
    private fun deleteFriend(userName: String) {

    }

    class ContactListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {}
}