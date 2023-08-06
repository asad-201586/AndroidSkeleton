package com.issue_tracker.android.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

interface KotlinBaseListener {
    fun openAForResult(kClass: KClass<out AppCompatActivity>, bundle: Bundle, code: Int){}
    fun showProgress(){}
    fun hideProgress(){}
}