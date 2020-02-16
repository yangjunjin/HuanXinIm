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
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RegisterActivity : BaseActivity(), RegisterContract.View{
    val presenter = RegisterPresenter(this)
    override fun getLayoutResId(): Int {
        return R.layout.activity_register
    }
}
