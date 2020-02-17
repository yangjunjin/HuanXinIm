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
import org.jetbrains.anko.doAsync


/**
 * author : yangjunjin
 * date : 2020/2/15 11:49
 */
class ContactsPresenter(val view: ContactsContract.View) : ContactsContract.Presenter {

    //加载联系人的数据
    override fun loadContacts() {
        doAsync {
            try{
                val usernames  = EMClient.getInstance().contactManager().allContactsFromServer
                uiThread { view.onLoadContactsFailed()}
            }catch (e:HyphenateException){
                uiThread { view.onLoadContactsFailed() }
            }
        }
    }
}
