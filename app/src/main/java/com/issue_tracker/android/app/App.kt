package com.issue_tracker.android.app

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.issue_tracker.android.utils.SharedPreferenceUtils
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        myApplication = this

        initLoggers()

    }

    companion object {

        var myApplication: Context? = null
            private set

        @JvmStatic
        val staticContext: Context
            get() = myApplication!!.applicationContext
    }

    private fun initLoggers() {

        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .methodCount(0)
            .methodOffset(5)
            .build()

        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))

        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String {
                return String.format(
                    "Class:%s: Line: %s, Method: %s",
                    super.createStackElementTag(element),
                    element.lineNumber,
                    element.methodName
                )
            }

            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                Logger.log(priority, tag, message, t)
            }
        })

        Timber.i("inside App!")
    }


    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }


}