package com.issue_tracker.android.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import com.google.gson.Gson
import com.issue_tracker.android.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class SharedPreferenceUtils @Inject constructor(@ApplicationContext context: Context) {
    val mSharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(
            context.resources?.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }
    var mEditor: SharedPreferences.Editor = mSharedPreferences.edit()

    inline fun <reified T : Any> get(key: String, defaultValue: T): T {
        val encodedKey = encode(key)
        var value = mSharedPreferences.getString(encodedKey, "default")
        value = if (value == "default") {
            defaultValue.toString()
        } else {
            decode(value!!)
        }
        return when (T::class) {
            String::class -> value as T
            Int::class -> value.toInt() as T
            Long::class -> value.toLong() as T
            Boolean::class -> value.toBoolean() as T
            else -> throw IllegalArgumentException("This default value type is not accepted only pass String, Long, Int, Boolean.")
        }
    }

    inline fun <reified T : Any> set(key: String, value: T) {
        val encryptedKey = encode(key)
        val encodedValue = encode(value.toString())
        mEditor.putString(encryptedKey, encodedValue).also { mEditor.apply() }
    }

    inline fun <reified T> setPrefObject(key: String, obj: T) {
        set(key, Gson().toJson(obj))
    }

    inline fun <reified T> getPrefObject(key: String): T {
        return Gson().fromJson(get(key, ""), T::class.java)
    }

    inline fun <reified T> clear(vararg restore: Pair<String, T>) {
        mEditor.clear()
        mEditor.apply()
        restore.forEach {
            when (T::class) {
                String::class -> set(it.first, it.second as String)
                Int::class -> set(it.first, it.second as Int)
                Long::class -> set(it.first, it.second as Long)
                Boolean::class -> set(it.first, it.second as Boolean)
                else -> throw IllegalArgumentException("This value type is not accepted only pass String, Long, Int, Boolean.")
            }
        }
    }

    fun clearAll() {
        mEditor.clear()
        mEditor.apply()
    }

    fun encode(plain: String): String {
        val b64encoded = Base64.encodeToString(plain.toByteArray(), Base64.DEFAULT)

        // Reverse the string
        val reverse = StringBuffer(b64encoded).reverse().toString()

        val tmp = StringBuilder()
        val offset = 4
        for (element in reverse) {
            tmp.append((element.code + offset).toChar())
        }
        return tmp.toString()
    }

    fun decode(secret: String): String {
        val tmp = StringBuilder()
        val offset = 4
        for (element in secret) {
            tmp.append((element.code - offset).toChar())
        }

        val reversed = StringBuffer(tmp.toString()).reverse().toString()
        return String(Base64.decode(reversed, Base64.DEFAULT))
    }

}

fun Context.clearPref() {
    val prefs = this.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
    val editor = prefs.edit()
    editor?.clear()?.apply()
}