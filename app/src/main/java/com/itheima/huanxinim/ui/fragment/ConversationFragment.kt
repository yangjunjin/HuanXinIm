package com.itheima.huanxinim.ui.fragment

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import com.itheima.huanxinim.R
import com.itheima.huanxinim.adapter.ConversationListAdapter
import com.itheima.huanxinim.adapter.EMMessageListenerAdapter
import com.itheima.huanxinim.base.BaseFragment
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.fragment_conversation.recyclerView
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
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    override fun onResume() {
        super.onResume()
        loadConversations()
    }

    //接收消息回调
    private var messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            Log.e("messageListener==", "收到消息")
            loadConversations()
        }
    }

    /**
     * 下载会话列表
     */
    private fun loadConversations() {
        doAsync {
            conversations.clear()
            val allConversations = EMClient.getInstance().chatManager().allConversations
            conversations.addAll(allConversations.values)
            uiThread { recyclerView.adapter?.notifyDataSetChanged() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}














