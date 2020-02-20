@file:Suppress("DEPRECATION")

package com.itheima.huanxinim.base

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager

/**
 * 所有Activity的基类
 */

abstract class BaseActivity : AppCompatActivity() {

    private val progressDialog by lazy { ProgressDialog(this) }
    private val inputMethodManager by lazy { getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }

    open fun init() {
    }

    abstract fun getLayoutResId(): Int

    /**
     * 显示进度条
     */
    fun showProgress(message: String) {
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    /**
     * 隐藏
     */
    fun dismissProgress() {
        progressDialog.dismiss()
    }

    /**
     * 隐藏键盘
     */
    fun hideSoftKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

}
