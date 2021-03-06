package com.itheima.huanxinim.app

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioManager
import android.media.SoundPool
import android.util.Log
import cn.bmob.v3.Bmob
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions
import com.hyphenate.chat.EMTextMessageBody
import com.itheima.huanxinim.BuildConfig
import com.itheima.huanxinim.R
import com.itheima.huanxinim.adapter.EMMessageListenerAdapter
import com.itheima.huanxinim.ui.activity.ChatActivity

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
            if (isForeground()) {//前台
                soundPool.play(duan, 1f, 1f, 0, 0, 1f)
            } else {//后台
                soundPool.play(yulu, 1f, 1f, 0, 0, 1f)
                showNotification(p0)
            }
        }
    }

    //通知栏
    private fun showNotification(p0: MutableList<EMMessage>?) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        p0?.forEach {
            var contentText = "IM的消息"
            if (it.type == EMMessage.Type.TXT) {
                contentText = (it.body as EMTextMessageBody).message
            }
            val intent = Intent(this,ChatActivity::class.java)
            intent.putExtra("userName",it.conversationId())
//            val pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
            val taskStaBuilder = TaskStackBuilder.create(this).addParentStack(ChatActivity::class.java).addNextIntent(intent)
            val pendingIntent = taskStaBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
            val notification = Notification.Builder(this)
                .setContentTitle("头部文字")
                .setContentText(contentText)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .notification
            notificationManager.notify(1, notification)
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