package com.itheima.huanxinim.contract

/**
 * author : yangjunjin
 * date : 2020/2/15 11:27
 */
interface ContactsContract {
    interface Presenter : BasePresenter {
        fun loadContacts()
    }

    interface View {
        fun onLoadContactsSuccess()
        fun onLoadContactsFailed()
    }
}