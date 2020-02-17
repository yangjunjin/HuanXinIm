package com.itheima.huanxinim.ui.activity

import com.itheima.huanxinim.R
import com.itheima.huanxinim.base.BaseActivity
import com.itheima.huanxinim.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        super.init()
        bottomBar.setOnTabSelectListener { tabId ->
            val beginTransaction = supportFragmentManager.beginTransaction()
            FragmentFactory.instance.getFragment(tabId)?.let { beginTransaction.replace(R.id.fragment_frame, it) }
            beginTransaction.commit()
        }
    }
}
