package com.itheima.huanxinim.presenter

import cn.bmob.v3.BmobUser
import com.itheima.huanxinim.contract.RegisterContract
import com.itheima.huanxinim.extentions.isValidPassword
import com.itheima.huanxinim.extentions.isValidUserName
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync


/**
 * author : yangjunjin
 * date : 2020/2/15 11:49
 */
class RegisterPresenter(val view: RegisterContract.View) : RegisterContract.Presenter {
    override fun register(userName: String, password: String, confirmPassword: String) {
        if (userName.isValidUserName()) {
            if (password.isValidPassword()) {
                if (confirmPassword.isValidPassword()) {
                    view.onStartRegister()
                    //开始注册
                    registerBmob(userName, password, confirmPassword)
                } else {
                    view.onConfirmPasswordError()
                }
            } else {
                view.onPasswordError()
            }
        } else {
            view.onUserNameError()
        }
    }

    private fun registerBmob(userName: String, password: String, confirmPassword: String) {
        val bu = BmobUser()
        bu.username = userName
        bu.setPassword(password)
        bu.signUp<BmobUser>(object : SaveListener<BmobUser>() {
            override fun done(p0: BmobUser?, p1: BmobException?) {
                if (p1 == null) {
                    //Bmob注册成功，注册到环信
                    registerEaseMob(userName, password)
                } else {
                    //注册失败
                    uiThread {  view.onRegisterFailed() }
                }
            }
        })
    }

    //注册到环信
    private fun registerEaseMob(userName: String, password: String) {
        doAsync {
            try {
                //注册失败会抛出异常
                EMClient.getInstance().createAccount(userName,password)
                uiThread {  view.onRegisterSuccess() }
            } catch (e: HyphenateException) {
                uiThread {  view.onRegisterFailed() }
            }
        }
    }
}
