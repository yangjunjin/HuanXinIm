package com.itheima.huanxinim.ui.activity

import android.text.Editable
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.itheima.huanxinim.R
import com.itheima.huanxinim.adapter.EMMessageListenerAdapter
import com.itheima.huanxinim.adapter.MessageListAdapter
import com.itheima.huanxinim.adapter.TextWatcherAdapter
import com.itheima.huanxinim.base.BaseActivity
import com.itheima.huanxinim.contract.ChatContract
import com.itheima.huanxinim.presenter.ChatPresenter
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class ChatActivity : BaseActivity(), ChatContract.View {

    val presenter = ChatPresenter(this)
    lateinit var username: String

    override fun getLayoutResId(): Int {
        return R.layout.activity_chat
    }

    override fun init() {
        super.init()
        initHeader()
        initEditText()
        initRecyclerView()
        send.setOnClickListener { send() }
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        presenter.loadMessages(username)
    }

    //接收消息回调
    private var messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            Log.e("messageListener==", "收到消息")
            presenter.addMessage(username, p0)
            runOnUiThread {
                recyclerView.adapter?.notifyDataSetChanged()
                scrollToBottom()
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MessageListAdapter(context, presenter.messages)
        }
    }

    //发送消息
    fun send() {
        hideSoftKeyboard()
        var message = edit.text.toString().trim()
        presenter.sendMessage(username, message)
    }

    //初始化发送的按钮
    private fun initEditText() {
        edit.addTextChangedListener(object : TextWatcherAdapter() {
            override fun afterTextChanged(p0: Editable?) {
                send.isEnabled = !p0.isNullOrEmpty()
            }
        })

        edit.setOnEditorActionListener { textView, i, keyEvent ->
            send()
            true
        }
    }

    //初始化头部
    private fun initHeader() {
        back.setOnClickListener { finish() }
        //获取聊天的用户名
        username = intent.getStringExtra("userName")
        val titleString = String.format(getString(R.string.chat_with), username)
        headerTitle.text = titleString
    }

    override fun onStartSendMessage() {
        //通知RecyclerView刷新列表
        recyclerView.adapter?.notifyDataSetChanged()
        scrollToBottom()
    }

    /**
     * 滚动到底部
     */
    private fun scrollToBottom() {
        recyclerView.scrollToPosition(presenter.messages.size - 1)
    }

    override fun onSendMessageSuccess() {
        recyclerView.adapter?.notifyDataSetChanged()
        toast(R.string.send_success)
        //清空编辑框
        edit.text.clear()
    }

    override fun onSendMessageFailed() {
        recyclerView.adapter?.notifyDataSetChanged()
        toast(R.string.send_error)
    }

    /**
     * 初始化消息
     */
    override fun onMessageLoaded() {
        recyclerView.adapter?.notifyDataSetChanged()
        scrollToBottom()
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}
