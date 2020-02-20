package com.itheima.huanxinim.ui.activity

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.itheima.huanxinim.R
import com.itheima.huanxinim.adapter.AddFriendListAdapter
import com.itheima.huanxinim.base.BaseActivity
import com.itheima.huanxinim.contract.AddFriendContract
import com.itheima.huanxinim.presenter.AddFriendPresenter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class AddFriendActivity : BaseActivity(), AddFriendContract.View{

    val presenter = AddFriendPresenter(this)
    override fun getLayoutResId(): Int {
        return R.layout.activity_add_friend
    }

    override fun init() {
        super.init()
        headerTitle.text = "添加联系人"
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context,presenter.addFriendItems)
        }

        search.setOnClickListener { search() }
        userName.setOnEditorActionListener { textView, i, keyEvent ->
            search()
            true
        }
    }

    /**
     * 搜索
     */
    private fun search(){
        hideSoftKeyboard()
        showProgress(getString(R.string.search))
        var key = userName.text.toString()
        presenter.search(key)
    }
    /**
     * 搜索成功
     */
    override fun onSearchSuccess() {
        toast(R.string.success)
        dismissProgress()
        recyclerView.adapter?.notifyDataSetChanged()
    }

    /**
     * 搜索失败
     */
    override fun onSearchError() {
        toast(R.string.error)
        dismissProgress()
    }
}
