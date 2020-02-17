package com.itheima.huanxinim.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.itheima.huanxinim.R
import com.itheima.huanxinim.base.BaseActivity
import com.itheima.huanxinim.contract.LoginContract
import com.itheima.huanxinim.contract.RegisterContract
import com.itheima.huanxinim.presenter.LoginPresenter
import com.itheima.huanxinim.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_login.userName
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RegisterActivity : BaseActivity(), RegisterContract.View {


    val presenter = RegisterPresenter(this)
    override fun getLayoutResId(): Int {
        return R.layout.activity_register
    }

    override fun init() {
        super.init()
        register.setOnClickListener { register() }
        confirmPassword.setOnEditorActionListener { textView, i, keyEvent ->
            register()
            true
        }
    }

    //注册
    fun register() {
        hideSoftKeyboard()
        val userNameString=userName.text.trim().toString()
        val passwordString=password.text.trim().toString()
        val confirmPassword = confirmPassword.text.trim().toString()
        presenter.register(userNameString,passwordString,confirmPassword)
    }

    override fun onUserNameError() {
        userName.error = "用户名出错"
    }

    override fun onPasswordError() {
        password.error = "密码出错"
    }

    override fun onConfirmPasswordError() {
        confirmPassword.error = "确认密码出错"
    }

    override fun onStartRegister() {
        showProgress("正在注册")
    }

    override fun onRegisterSuccess() {
        toast("注册成功")
        dismissProgress()
        finish()
    }

    override fun onRegisterFailed() {
        dismissProgress()
        toast("注册失败")
    }

    override fun onUserExit() {
        dismissProgress()
        toast("用户已经存在")
    }
}
