package com.itheima.huanxinim.adapter

import com.hyphenate.EMContactListener

/**
 * author : yangjunjin
 * date : 2020/2/18 13:41
 */
open class EMContactListenerAdapter: EMContactListener{
    //邀请
    override fun onContactInvited(p0: String?, p1: String?) {
    }
    //删除
    override fun onContactDeleted(p0: String?) {
    }
    //接受
    override fun onFriendRequestAccepted(p0: String?) {
    }
    //添加
    override fun onContactAdded(p0: String?) {
    }
    //拒绝
    override fun onFriendRequestDeclined(p0: String?) {
    }
}