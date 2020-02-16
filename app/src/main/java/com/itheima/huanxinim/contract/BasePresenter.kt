package com.itheima.huanxinim.contract

import android.os.Handler
import android.os.Looper

/**
 * author : yangjunjin
 * date : 2020/2/15 11:27
 */
interface BasePresenter {
    companion object {
        val handler by lazy { Handler(Looper.getMainLooper()) }
    }

    fun uiThread(f:()->Unit){
        handler.post{f()}
    }
}