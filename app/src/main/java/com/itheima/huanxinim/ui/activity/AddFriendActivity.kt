package com.itheima.huanxinim.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.itheima.huanxinim.R
import com.itheima.huanxinim.adapter.AddFriendListAdapter
import com.itheima.huanxinim.base.BaseActivity
import com.itheima.huanxinim.contract.AddFriendContract
import com.itheima.huanxinim.contract.ChatContract
import com.itheima.huanxinim.contract.LoginContract
import com.itheima.huanxinim.presenter.AddFriendPresenter
import com.itheima.huanxinim.presenter.ChatPresenter
import com.itheima.huanxinim.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class AddFriendActivity : BaseActivity(), AddFriendContract.View{
    val presenter = AddFriendPresenter(this)
    override fun getLayoutResId(): Int {
        return R.layout.activity_add_friend
    }

    override fun init() {
        super.init()
        headerTitle.text = "添加联系人"
        back.setOnClickListener { finish() }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context)
        }
    }
}
