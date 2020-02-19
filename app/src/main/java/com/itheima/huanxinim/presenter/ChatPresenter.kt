package com.itheima.huanxinim.presenter

import android.media.MediaPlayer
import android.util.Log
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.itheima.huanxinim.adapter.EMCallBackAdapter
import com.itheima.huanxinim.contract.ChatContract
import com.itheima.huanxinim.contract.LoginContract
import com.itheima.huanxinim.contract.SplashContract
import com.itheima.huanxinim.extentions.isValidPassword
import com.itheima.huanxinim.extentions.isValidUserName

/**
 * author : yangjunjin
 * date : 2020/2/15 11:49
 */
class ChatPresenter(val view: ChatContract.View) : ChatContract.Presenter {
    val messages = mutableListOf<EMMessage>()

    override fun sendMessage(contact: String, message: String) {
        //创建一条消息
        val emMessage = EMMessage.createTxtSendMessage(message, contact)
        emMessage.setMessageStatusCallback(object : EMCallBackAdapter() {
            override fun onSuccess() {
                super.onSuccess()
                uiThread { view.onSendMessageSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                uiThread { view.onSendMessageFailed() }
            }
        })
        messages.add(emMessage)
        view.onStartSendMessage()
        EMClient.getInstance().chatManager().sendMessage(emMessage)
    }
}