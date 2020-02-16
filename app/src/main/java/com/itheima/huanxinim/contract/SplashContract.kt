package com.itheima.huanxinim.contract

/**
 * author : yangjunjin
 * date : 2020/2/15 11:27
 */
interface SplashContract {
    interface Presenter:BasePresenter{
        fun checkLoginStatus()//检查登录状态
    }

    interface View{
        fun onNotLoggedIn()//没有登陆的UI的处理
        fun onLoggedIn()//已经登陆的UI的处理
    }
}