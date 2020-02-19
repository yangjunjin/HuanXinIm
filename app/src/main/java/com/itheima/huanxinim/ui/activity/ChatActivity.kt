package com.itheima.huanxinim.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.text.Editable
import android.text.TextWatcher
import androidx.core.app.ActivityCompat
import com.itheima.huanxinim.R
import com.itheima.huanxinim.adapter.TextWatcherAdapter
import com.itheima.huanxinim.base.BaseActivity
import com.itheima.huanxinim.contract.ChatContract
import com.itheima.huanxinim.contract.LoginContract
import com.itheima.huanxinim.presenter.ChatPresenter
import com.itheima.huanxinim.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ChatActivity : BaseActivity(), ChatContract.View{
    val presenter = ChatPresenter(this)
    override fun getLayoutResId(): Int {
        return R.layout.activity_chat
    }

    override fun init() {
        super.init()
        initHeader()
        initEditText()
    }

    private fun initEditText() {
        edit.addTextChangedListener(object :TextWatcherAdapter(){
            override fun afterTextChanged(p0: Editable?) {
             send.isEnabled = !p0.isNullOrEmpty()
            }
        })
    }

    //初始化头部
    private fun initHeader() {
        back.setOnClickListener { finish() }
        //获取聊天的用户名
        val username = intent.getStringExtra("userName")
        val titleString = String.format(getString(R.string.chat_with),username)
        headerTitle.text = titleString
    }
}
