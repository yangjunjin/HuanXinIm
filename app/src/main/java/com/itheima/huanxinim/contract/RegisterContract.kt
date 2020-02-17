package com.itheima.huanxinim.contract

/**
 * author : yangjunjin
 * date : 2020/2/15 11:27
 */
interface RegisterContract {
    interface Presenter : BasePresenter {
        fun register(userName:String,password:String,confirmPassword:String)
    }

    interface View {
        fun onUserNameError()
        fun onPasswordError()
        fun onConfirmPasswordError()
        fun onStartRegister()
        fun onRegisterSuccess()
        fun onRegisterFailed()
        fun onUserExit()
    }
}