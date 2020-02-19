package com.itheima.huanxinim.presenter

import android.media.MediaPlayer
import android.util.Log
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hyphenate.chat.EMClient
import com.itheima.huanxinim.adapter.EMCallBackAdapter
import com.itheima.huanxinim.contract.AddFriendContract
import com.itheima.huanxinim.contract.ChatContract
import com.itheima.huanxinim.contract.LoginContract
import com.itheima.huanxinim.contract.SplashContract
import com.itheima.huanxinim.data.AddFriendItem
import com.itheima.huanxinim.data.db.IMDatabase
import com.itheima.huanxinim.extentions.isValidPassword
import com.itheima.huanxinim.extentions.isValidUserName
import org.jetbrains.anko.doAsync

/**
 * author : yangjunjin
 * date : 2020/2/15 11:49
 */
class AddFriendPresenter(val view: AddFriendContract.View) : AddFriendContract.Presenter {

    val addFriendItems = mutableListOf<AddFriendItem>()
    override fun search(key: String) {
        addFriendItems.clear()
        val query = BmobQuery<BmobUser>()
        query.addWhereContains("username", key)
            .addWhereNotEqualTo("username", EMClient.getInstance().currentUser)
        query.findObjects(object : FindListener<BmobUser>() {
            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {
                if (p1 == null) {
                    //数据的处理
                    //创建AddFriendItem的集合
                    val allContacts = IMDatabase.instance.getAllContacts()
                    doAsync {
                        p0?.forEach {
                            //对比是否已经添加过
                            var isAdded = false
                            for(contact in allContacts){
                                if(contact.name==it.username){
                                    isAdded = true
                                }
                            }
                            val addFriendItem = AddFriendItem(it.username, it.createdAt,isAdded)
                            addFriendItems.add(addFriendItem)
                        }
                    }
                    uiThread { view.onSearchSuccess() }
                } else uiThread { view.onSearchError() }
            }
        })
    }
}