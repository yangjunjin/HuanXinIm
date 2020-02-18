package com.itheima.huanxinim.contract

/**
 * author : yangjunjin
 * date : 2020/2/15 11:27
 */
interface AddFriendContract {
    interface Presenter : BasePresenter {
        fun search(key: String)
    }

    interface View {
        fun onSearchSuccess()
        fun onSearchError()
    }
}