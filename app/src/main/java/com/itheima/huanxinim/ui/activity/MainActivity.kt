package com.itheima.huanxinim.ui.activity

import android.util.Log
import com.hyphenate.EMConnectionListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.itheima.huanxinim.R
import com.itheima.huanxinim.adapter.EMMessageListenerAdapter
import com.itheima.huanxinim.base.BaseActivity
import com.itheima.huanxinim.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

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
        EMClient.getInstance().addConnectionListener(connectListener)
    }

    //连接的监听
    private var connectListener = object :EMConnectionListener{
        override fun onConnected() {
            Log.e("MainActivity==环信==","连接成功")
        }

        override fun onDisconnected(p0: Int) {
            Log.e("MainActivity==环信==","其他设备登陆")
            runOnUiThread { toast("其他设备登陆") }
            startActivity<LoginActivity>()
            finish()
        }
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
