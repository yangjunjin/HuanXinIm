package com.itheima.huanxinim.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyphenate.EMContactListener
import com.hyphenate.chat.EMClient
import com.itheima.huanxinim.R
import com.itheima.huanxinim.adapter.ContactListAdapter
import com.itheima.huanxinim.adapter.EMContactListenerAdapter
import com.itheima.huanxinim.base.BaseFragment
import com.itheima.huanxinim.contract.ContactsContract
import com.itheima.huanxinim.presenter.ContactsPresenter
import com.itheima.huanxinim.widget.SlideBar
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.support.v4.toast

/**
 * author : yangjunjin
 * date : 2020/2/17 14:54
 */
class ContactsFragment : BaseFragment(), ContactsContract.View {

    val presenter = ContactsPresenter(this)
    override fun getLayoutResId(): Int {
        return R.layout.fragment_contacts
    }

    override fun init() {
        super.init()
        headerTitle.text = "联系人"
        add.visibility = View.VISIBLE
        add.setOnClickListener { toast("添加联系人") }

        swipeRefreshLayout.apply {
            isRefreshing = true
            setColorSchemeResources(R.color.colorAccent)
            setOnRefreshListener { presenter.loadContacts() }
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context, presenter.contactListItems)
        }

        //删除好友监听，刷新列表
        EMClient.getInstance().contactManager().setContactListener(object : EMContactListenerAdapter() {
            override fun onContactDeleted(p0: String?) {
                presenter.loadContacts()
            }
        })

        //SlideBar的监听
        slideBar.onSectionChangeListener = object : SlideBar.OnSectionChangeListener {
            override fun onSectionChange(firstLetter: String) {
                section.visibility = View.VISIBLE
                section.text = firstLetter
                recyclerView.smoothScrollToPosition(getPosition(firstLetter))
            }

            override fun onSlideFinish() {
                section.visibility = View.GONE
            }
        }

        presenter.loadContacts()
    }

    /**
     * 查找到这个字母所在的位置
     */
    private fun getPosition(firstLetter: String): Int =
        presenter.contactListItems.binarySearch { contactListItem ->
            contactListItem.firstLetter.minus(firstLetter[0])
        }

    override fun onLoadContactsSuccess() {
        swipeRefreshLayout?.isRefreshing = false
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onLoadContactsFailed() {
        toast("联系人加载失败")
        swipeRefreshLayout.isRefreshing = false
    }
}