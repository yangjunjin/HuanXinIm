package com.itheima.huanxinim.ui.activity

import android.os.Handler
import com.hyphenate.chat.EMChatService

import com.itheima.huanxinim.R
import com.itheima.huanxinim.base.BaseActivity
import com.itheima.huanxinim.contract.SplashContract
import com.itheima.huanxinim.presenter.SplashPresenter
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity(), SplashContract.View {
    val presenter = SplashPresenter(this)

    companion object{
        const val DELAY = 2000L
    }

    private val handler by lazy { Handler() }

    override fun getLayoutResId(): Int {
        return R.layout.activity_splash
    }

    override fun init() {
        super.init()
        presenter.checkLoginStatus()
    }

    //没有登陆的处理
    override fun onNotLoggedIn() {
        handler.postDelayed({
            startActivity<LoginActivity>()
            finish()
        },DELAY)
    }

    //已经登陆的处理
    override fun onLoggedIn() {
        startActivity<MainActivity>()
        finish()
    }
}
