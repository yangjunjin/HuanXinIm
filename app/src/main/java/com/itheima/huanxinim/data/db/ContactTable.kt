package com.itheima.huanxinim.data.db

/**
 * author : yangjunjin
 * date : 2020/2/18 17:53
 */
//1、定义了ContactTable
//2、创建了ContactTable的一个实例，通过类名，直接访问实例，实现单例的一种方式
object ContactTable {
    val NAME="contact"//表的名字
    //数据库的字段
    val ID = "_id"
    val CONTACT = "name"
}