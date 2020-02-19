package com.itheima.huanxinim.data.db

import com.itheima.huanxinim.extentions.toVararyArray
import kotlinx.coroutines.selects.select
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * author : yangjunjin
 * date : 2020/2/19 12:02
 */
class IMDatabase {
    companion object{
        val databaseHelper = DatabaseHelper()
        val instance = IMDatabase()
    }

    /**
     * 保存一个对象
     */
    fun saveContact(contact: Contact){
        databaseHelper.use {
            //SQLiteDatabase的扩展方法
            insert(ContactTable.NAME,*contact.map.toVararyArray())
        }
    }

    /**
     * 获取所有好友
     */
    fun getAllContacts():List<Contact>{
      return  databaseHelper.use {
            select(ContactTable.NAME).parseList(object :MapRowParser<Contact>{
                override fun parseRow(columns: Map<String, Any?>): Contact {
                    return Contact(columns.toMutableMap())
                }
            })
        }
    }

    /**
     * 删除所有的好友
     */
    fun deleteAllContacts(){
        databaseHelper.use{
            delete(ContactTable.NAME,null,null)
        }
    }
}