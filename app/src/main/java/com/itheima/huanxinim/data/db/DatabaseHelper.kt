package com.itheima.huanxinim.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.itheima.huanxinim.app.IMApplication
import org.jetbrains.anko.db.*

/**
 * author : yangjunjin
 * date : 2020/2/19 11:37
 */
class DatabaseHelper(ctx: Context=IMApplication.instance) :
    ManagedSQLiteOpenHelper(ctx, NAME, null, VERSION) {

    companion object{
        val NAME = "im.db"
        val VERSION = 1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.createTable(ContactTable.NAME,true,
            ContactTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            ContactTable.CONTACT to TEXT)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.dropTable(ContactTable.NAME,true)
        onCreate(p0)
    }
}