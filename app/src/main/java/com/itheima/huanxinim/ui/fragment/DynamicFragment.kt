package com.itheima.huanxinim.ui.fragment

import android.util.Log
import com.hyphenate.chat.EMClient
import com.itheima.huanxinim.R
import com.itheima.huanxinim.adapter.EMCallBackAdapter
import com.itheima.huanxinim.base.BaseFragment
import com.itheima.huanxinim.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

/**
 * author : yangjunjin
 * date : 2020/2/17 14:54
 */
class DynamicFragment : BaseFragment() {
    override fun getLayoutResId(): Int {
        return R.layout.fragment_dynamic
    }

    override fun init() {
        super.init()
        headerTitle.text = "动态"

        val logoutString = String.format(getString(R.string.login_out), EMClient.getInstance().currentUser)
        loginOut.text = logoutString

        loginOut.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        EMClient.getInstance().logout(true,object : EMCallBackAdapter() {
            override fun onSuccess() {
                super.onSuccess()
                context?.runOnUiThread {
                    toast("退出成功")
                    context?.startActivity<LoginActivity>()
                    activity?.finish()
                }
            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                context?.runOnUiThread {
                    toast("退出失败")
                }
            }
        })
    }
}