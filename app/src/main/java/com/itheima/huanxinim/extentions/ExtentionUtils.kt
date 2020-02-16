package com.itheima.huanxinim.extentions

/**
 * author : yangjunjin
 * date : 2020/2/15 17:53
 */
fun String.isValidUserName(): Boolean = this.matches(Regex("^[a-zA-Z]\\w{2,19}$"))

fun String.isValidPassword(): Boolean = this.matches(Regex("^[0-9]\\w{3,20}$"))