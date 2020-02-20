package com.itheima.huanxinim.ui.activity

import android.util.Log
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.itheima.huanxinim.R
import com.itheima.huanxinim.adapter.EMMessageListenerAdapter
import com.itheima.huanxinim.base.BaseActivity
import com.itheima.huanxinim.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        super.init()
        bottomBar.setOnTabSelectListener { tabId ->
            val beginTransaction = supportFragmentManager.beginTransaction()
            FragmentFactory.instance.getFragment(tabId)?.let { beginTransaction.replace(R.id.fragment_frame, it) }
            beginTransaction.commit()
        }

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    //接收消息回调
    private var messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            Log.e("messageListener==", "收到消息")
            updateBottomBarUnRead()
        }
    }

    override fun onResume() {
        super.onResume()
        updateBottomBarUnRead()
    }

    private fun updateBottomBarUnRead() {
        runOnUiThread {
            //初始化bottombar未读消息的个数
            val tab = bottomBar.getTabWithId(R.id.tab_conversation)
            tab.setBadgeCount(EMClient.getInstance().chatManager().unreadMessageCount)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}
