package com.itheima.huanxinim.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.itheima.huanxinim.R
import com.itheima.huanxinim.base.BaseActivity
import com.itheima.huanxinim.contract.ChatContract
import com.itheima.huanxinim.contract.LoginContract
import com.itheima.huanxinim.presenter.ChatPresenter
import com.itheima.huanxinim.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ChatActivity : BaseActivity(), ChatContract.View{
    val presenter = ChatPresenter(this)
    override fun getLayoutResId(): Int {
        return R.layout.activity_chat
    }

    override fun init() {
        super.init()

    }
}
