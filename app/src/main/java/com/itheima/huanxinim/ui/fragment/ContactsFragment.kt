package com.itheima.huanxinim.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyphenate.chat.EMClient
import com.itheima.huanxinim.R
import com.itheima.huanxinim.adapter.ContactListAdapter
import com.itheima.huanxinim.adapter.EMContactListenerAdapter
import com.itheima.huanxinim.base.BaseFragment
import com.itheima.huanxinim.contract.ContactsContract
import com.itheima.huanxinim.presenter.ContactsPresenter
import com.itheima.huanxinim.ui.activity.AddFriendActivity
import com.itheima.huanxinim.widget.SlideBar
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
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
        initHeader()
        initSwipeRefreshLayout()
        initRecyclerView()
        //删除好友监听，刷新列表
        EMClient.getInstance().contactManager().setContactListener(contactListener)
        initSlideBar()
        presenter.loadContacts()
    }

    //SlideBar的监听
    private fun initSlideBar() {
        slideBar.onSectionChangeListener = object : SlideBar.OnSectionChangeListener {
            override fun onSectionChange(firstLetter: String) {
                section.visibility = View.VISIBLE
                section.text = firstLetter
                recyclerView?.smoothScrollToPosition(getPosition(firstLetter))
            }

            override fun onSlideFinish() {
                section.visibility = View.GONE
            }
        }
    }

    val contactListener = object : EMContactListenerAdapter() {
        override fun onContactDeleted(p0: String?) {
            presenter.loadContacts()
        }

        //别人添加的消息的回调
        override fun onContactAdded(p0: String?) {
            presenter.loadContacts()
        }
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context, presenter.contactListItems)
        }
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.apply {
            isRefreshing = true
            setColorSchemeResources(R.color.colorAccent)
            setOnRefreshListener { presenter.loadContacts() }
        }
    }

    private fun initHeader() {
        headerTitle.text = "联系人"
        add.visibility = View.VISIBLE
        add.setOnClickListener {
            context?.startActivity<AddFriendActivity>()
        }
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

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().contactManager().removeContactListener(contactListener)
    }
}