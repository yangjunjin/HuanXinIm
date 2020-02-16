package com.itheima.huanxinim.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.itheima.huanxinim.R
import com.itheima.huanxinim.base.BaseActivity
import com.itheima.huanxinim.contract.LoginContract
import com.itheima.huanxinim.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity(),LoginContract.View{
    val presenter = LoginPresenter(this)
    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun init() {
        super.init()
        login.setOnClickListener { login() }
        password.setOnEditorActionListener { textView, i, keyEvent ->
            login()
            true
        }
    }

    fun login(){
        //隐藏键盘
        hideSoftKeyboard()
        if(hasWriteExternalStoragePermission()){
            val userNameString = userName.text.trim().toString()
            val passwordString = password.text.trim().toString()
            presenter.login(userNameString,passwordString)
        }else{
            applyWriteExternalStoragePermission()
        }
    }

    /**
     * 申请权限
     */
    private fun applyWriteExternalStoragePermission() {
        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this,permissions,0)
    }

    //检查是否有写磁盘的权限
    private fun hasWriteExternalStoragePermission(): Boolean {
        val result = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    override fun onUserNameError() {
        userName.error = "用户名出错"
    }

    override fun onPassWordError() {
        password.error = "密码出错"
    }

    override fun onStartLogin() {
        showProgress("正在登陆。。。")
    }

    override fun onLoggedInSuccess() {
        dismissProgress()
        startActivity<MainActivity>()
        finish()
    }

    override fun onLoggedInFailed() {
        dismissProgress()
        toast("登陆失败")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            //用户同意权限，开始登陆
            login()
        }else{
            toast("用户拒绝权限")
        }
    }

}
