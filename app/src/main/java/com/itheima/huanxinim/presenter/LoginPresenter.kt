package com.itheima.huanxinim.presenter

import android.media.MediaPlayer
import com.hyphenate.chat.EMClient
import com.itheima.huanxinim.adapter.EMCallBackAdapter
import com.itheima.huanxinim.contract.LoginContract
import com.itheima.huanxinim.contract.SplashContract
import com.itheima.huanxinim.extentions.isValidPassword
import com.itheima.huanxinim.extentions.isValidUserName

/**
 * author : yangjunjin
 * date : 2020/2/15 11:49
 */
class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {
    override fun login(userName: String, password: String) {
        if(userName.isValidUserName()){
            if(password.isValidPassword()){
                view.onStartLogin()
                loginEaseMob(userName,password)
            }else{
             view.onPassWordError()
            }
        }else{
            view.onUserNameError()
        }
    }

    /**
     * IM的登陆
     */
    private fun loginEaseMob(userName: String, password: String) {
        EMClient.getInstance().login(userName,password,object :EMCallBackAdapter(){
            override fun onSuccess() {
                super.onSuccess()
                EMClient.getInstance().groupManager().loadAllGroups()
                EMClient.getInstance().chatManager().loadAllConversations()
                //回调在主线程
               uiThread { view.onLoggedInSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                uiThread { view.onLoggedInFailed() }
            }
        })
    }
}