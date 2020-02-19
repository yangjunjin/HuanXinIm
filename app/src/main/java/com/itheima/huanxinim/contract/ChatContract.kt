package com.itheima.huanxinim.contract

/**
 * author : yangjunjin
 * date : 2020/2/15 11:27
 */
interface ChatContract {
    interface Presenter : BasePresenter {
        fun sendMessage(contact: String, message: String)
    }

    interface View {
        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
    }
}