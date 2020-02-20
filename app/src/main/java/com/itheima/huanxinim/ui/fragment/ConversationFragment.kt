package com.itheima.huanxinim.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import com.itheima.huanxinim.R
import com.itheima.huanxinim.adapter.ConversationListAdapter
import com.itheima.huanxinim.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * author : yangjunjin
 * date : 2020/2/17 14:54
 */
class ConversationFragment:BaseFragment() {

    val conversations = mutableListOf<EMConversation>()
    override fun getLayoutResId(): Int {
        return R.layout.fragment_conversation
    }

    override fun init() {
        super.init()
        headerTitle.text = "消息"
        back.visibility = View.GONE

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context,conversations)
        }
        loadConversations()
    }

    /**
     * 下载会话列表
     */
    private fun loadConversations() {
        doAsync {
            val allConversations = EMClient.getInstance().chatManager().allConversations
            conversations.addAll(allConversations.values)
            uiThread { recyclerView.adapter?.notifyDataSetChanged() }
        }
    }
}














