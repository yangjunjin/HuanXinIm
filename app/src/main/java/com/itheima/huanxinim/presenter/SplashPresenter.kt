package com.itheima.huanxinim.presenter

import com.hyphenate.chat.EMClient
import com.itheima.huanxinim.contract.SplashContract

/**
 * author : yangjunjin
 * date : 2020/2/15 11:49
 */
class SplashPresenter(val view: SplashContract.View) : SplashContract.Presenter {
    override fun checkLoginStatus() {
        if (isLoggedIn()) view.onLoggedIn() else view.onNotLoggedIn()
    }

    private fun isLoggedIn(): Boolean = EMClient.getInstance().isConnected&&EMClient.getInstance().isLoggedInBefore
}