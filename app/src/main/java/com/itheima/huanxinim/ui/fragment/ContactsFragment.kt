package com.itheima.huanxinim.ui.fragment

import android.view.View
import com.itheima.huanxinim.R
import com.itheima.huanxinim.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*

/**
 * author : yangjunjin
 * date : 2020/2/17 14:54
 */
class ContactsFragment:BaseFragment() {
    override fun getLayoutResId(): Int {
        return R.layout.fragment_contacts
    }

    override fun init() {
        super.init()
        headerTitle.text = "联系人"
        add.visibility = View.VISIBLE
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.colorAccent)
            isRefreshing = true
        }
    }
}