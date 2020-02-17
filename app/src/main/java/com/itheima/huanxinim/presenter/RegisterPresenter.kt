package com.itheima.huanxinim.presenter

import com.itheima.huanxinim.contract.RegisterContract
import com.itheima.huanxinim.extentions.isValidPassword
import com.itheima.huanxinim.extentions.isValidUserName

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
                    register(userName,password,confirmPassword)
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

}