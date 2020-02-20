package com.itheima.huanxinim.contract

import com.hyphenate.chat.EMMessage

/**
 * author : yangjunjin
 * date : 2020/2/15 11:27
 */
interface ChatContract {
    interface Presenter : BasePresenter {
        fun sendMessage(contact: String, message: String)
        fun addMessage(username: String, p0: MutableList<EMMessage>?)
        fun loadMessages(username: String)
        fun loadMoreMessages(username: String)
    }

    interface View {
        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
        fun onMessageLoaded()
        fun onMoreMessageLoaded(size: Int)
    }
}