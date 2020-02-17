package com.itheima.huanxinim.factory
import androidx.fragment.app.Fragment
import com.itheima.huanxinim.R

import com.itheima.huanxinim.ui.fragment.ContactsFragment
import com.itheima.huanxinim.ui.fragment.ConversationFragment
import com.itheima.huanxinim.ui.fragment.DynamicFragment

/**
 * author : yangjunjin
 * date : 2020/2/17 15:00
 */
class FragmentFactory private constructor(){
    val conversation by lazy { ConversationFragment() }
    val contact by lazy { ContactsFragment() }
    val dynamic by lazy { DynamicFragment() }

    companion object{val instance = FragmentFactory()}

    fun getFragment(tabId:Int): Fragment?{
        when (tabId){
            R.id.tab_conversation->return conversation
            R.id.tab_contacts->return contact
            R.id.tab_dynamic->return dynamic
        }
        return null
    }
}