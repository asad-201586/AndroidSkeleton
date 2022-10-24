package com.example.testproject.app

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.example.testproject.di.common
import com.example.testproject.di.viewModels
import com.example.testproject.utils.SharedPreferenceUtils
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application(), LifecycleObserver {


    val preference by lazy { SharedPreferenceUtils(this) }
    override fun onCreate() {
        super.onCreate()
        application = this
        myApplication = this
        startKoin {
            androidContext(this@App)
            modules(listOf(viewModels, common))
        }

        initLoggers()

    }

    companion object {
        var isAppUpdateDialogDisplayed = false
        lateinit var application: App

        @JvmStatic
        fun getApp() = application

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

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        isAppUpdateDialogDisplayed = false
    }


}

fun getPref(): SharedPreferenceUtils {
    return App.getApp().preference
}