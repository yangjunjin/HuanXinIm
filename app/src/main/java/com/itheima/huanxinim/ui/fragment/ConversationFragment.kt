package com.itheima.huanxinim.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.itheima.huanxinim.R
import com.itheima.huanxinim.adapter.ConversationListAdapter
import com.itheima.huanxinim.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*

/**
 * author : yangjunjin
 * date : 2020/2/17 14:54
 */
class ConversationFragment:BaseFragment() {
    override fun getLayoutResId(): Int {
        return R.layout.fragment_conversation
    }

    override fun init() {
        super.init()
        headerTitle.text = "消息"
        back.visibility = View.GONE

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context)
        }
    }
}














