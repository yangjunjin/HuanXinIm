package com.itheima.huanxinim.presenter

import cn.bmob.v3.BmobUser
import com.itheima.huanxinim.contract.RegisterContract
import com.itheima.huanxinim.extentions.isValidPassword
import com.itheima.huanxinim.extentions.isValidUserName
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.itheima.huanxinim.contract.ContactsContract
import com.itheima.huanxinim.data.ContactListItem
import org.jetbrains.anko.doAsync


/**
 * author : yangjunjin
 * date : 2020/2/15 11:49
 */
class ContactsPresenter(val view: ContactsContract.View) : ContactsContract.Presenter {
    val contactListItems = mutableListOf<ContactListItem>()
    //加载联系人的数据
    override fun loadContacts() {
        contactListItems.clear()
        doAsync {
            try {
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                usernames.sortBy { it[0] }
                usernames.forEachIndexed { index, s ->
                    //判断是否显示
                    val showFirstLetter = index==0||(index>0&&s[0]!=usernames[index-1][0])
                    val contactListItem = ContactListItem(s, s[0].toUpperCase(),showFirstLetter)
                    contactListItems.add(contactListItem)
                }
                uiThread { view.onLoadContactsSuccess() }
            } catch (e: HyphenateException) {
                uiThread { view.onLoadContactsFailed() }
            }
        }
    }
}
