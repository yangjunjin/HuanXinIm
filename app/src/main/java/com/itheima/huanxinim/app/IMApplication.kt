package com.itheima.huanxinim.app

import android.app.Application
import cn.bmob.v3.Bmob
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import com.itheima.huanxinim.BuildConfig

/**
 * author : yangjunjin
 * date : 2020/2/15 17:12
 */
class IMApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        //初始化
        EMClient.getInstance().init(applicationContext, EMOptions())
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)
        Bmob.initialize(this, "0d029bc28f525d6cabfa7656b2137547")
    }
}