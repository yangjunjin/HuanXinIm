package com.itheima.huanxinim.app

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import android.util.Log
import cn.bmob.v3.Bmob
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions
import com.itheima.huanxinim.BuildConfig
import com.itheima.huanxinim.R
import com.itheima.huanxinim.adapter.EMMessageListenerAdapter

/**
 * author : yangjunjin
 * date : 2020/2/15 17:12
 */
class IMApplication : Application() {

    //获取应用的实例
    companion object {
        lateinit var instance: IMApplication
    }



    override fun onCreate() {
        super.onCreate()
        instance = this
        //初始化
        EMClient.getInstance().init(applicationContext, EMOptions())
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)
        Bmob.initialize(this, "0d029bc28f525d6cabfa7656b2137547")

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    val soundPool = SoundPool(2, AudioManager.STREAM_MUSIC, 0)
    val duan by lazy {
        soundPool.load(instance, R.raw.ring, 0)
    }
    val yulu by lazy {
        soundPool.load(instance, R.raw.ring, 0)
    }

    private var messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            Log.e("messageListener==", "收到消息")
            if (isForeground()) {
                soundPool.play(duan, 1f, 1f, 0, 0, 1f)
            } else {
                soundPool.play(yulu, 1f, 1f, 0, 0, 1f)
            }
        }
    }

    //是否前台
    private fun isForeground(): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (runningAppProcess in activityManager.runningAppProcesses) {
            if (runningAppProcess.processName == packageName) {
                return runningAppProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }
        }
        return false
    }
}