package com.issue_tracker.android.utils.extension

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.issue_tracker.android.R
import java.util.*

fun View.backPress() {
    this.setOnClickListener {
        (this.context as Activity).finish()
    }
}

inline fun <reified T> Activity.openActivity(extras: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.extras()
    startActivity(intent)
}

inline fun <reified T> Context.openActivity(extras: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.extras()
    startActivity(intent)
}


inline fun <reified T> Activity.openActivityWithFinish(extras: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.extras()
    startActivity(intent)
    finish()
}

inline fun <reified T> Fragment.openActivity(extras: Intent.() -> Unit = {}) {
    val intent = Intent(this.requireActivity(), T::class.java)
    intent.extras()
    startActivity(intent)
}

fun randomNumber(): Int {
    val min = 10000
    val max = 99999
    return Random().nextInt(max - min + 1) + min
}

fun Activity.goToAppPermissionSetting() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    startActivityForResult(intent, 111)
}

fun Activity.checkPhoneCallPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.CALL_PHONE
    ) == PackageManager.PERMISSION_GRANTED
}


fun Context.getColorCompat(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}

fun Activity.getColorCompat(@ColorRes color: Int): Int {
    return baseContext.getColorCompat(color)
}

fun Fragment.getColorCompat(@ColorRes color: Int): Int {
    return activity!!.getColorCompat(color)
}

fun Fragment.hideKeyboard() {
    activity?.hideKeyboard(view ?: View(activity))
}

fun Activity.hideKeyboard() {
    if (currentFocus == null) View(this) else currentFocus?.let { hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.openKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm!!.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}


fun Fragment.openKeyboard() {
    activity?.openKeyboard()
}

fun Activity.openKeyboard() {
    applicationContext?.openKeyboard()
}

inline fun EditText.observeTextChange(crossinline body: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            body(p0.toString())
        }
    })
}

inline fun TextView.observeTextChange(crossinline body: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            body(p0.toString())
        }
    })
}

infix fun ViewGroup.inflate(@LayoutRes view: Int): View {
    return LayoutInflater.from(context).inflate(view, this, false)
}

fun Int.inflate(viewGroup: ViewGroup): View {
    return LayoutInflater.from(viewGroup.context).inflate(this, viewGroup, false)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.toggleVisibility() {
    when (this.visibility) {
        View.VISIBLE -> this.gone()
        View.INVISIBLE -> this.visible()
        View.GONE -> this.visible()
    }
}

fun ImageView.setColorFilter(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
    } else {
        setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }
}

fun Drawable.setColorFilter(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
    } else {
        setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }
}

fun <T> T.isNull(): Boolean {
    return this == null
}

fun <T> T.isNotNull(): Boolean {
    return this != null
}


fun String.remove(vararg value: String): String {
    var removeString = this
    value.forEach {
        removeString = removeString.replace(it, "")
    }
    return removeString
}

fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

fun pxToDp(px: Int): Int {
    return (px / Resources.getSystem().displayMetrics.density).toInt()
}

fun spToPx(sp: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp.toFloat(),
        Resources.getSystem().displayMetrics
    )
}

fun View.setTopMargin(top: Int) {
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        val p = layoutParams as ViewGroup.MarginLayoutParams
        p.topMargin = dpToPx(top)
        requestLayout()
    }
}

fun View.setLeftMargin(left: Int) {
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        val p = layoutParams as ViewGroup.MarginLayoutParams
        p.leftMargin = dpToPx(top)
        requestLayout()
    }
}

fun View.setRightMargin(right: Int) {
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        val p = layoutParams as ViewGroup.MarginLayoutParams
        p.rightMargin = dpToPx(top)
        requestLayout()
    }
}

fun View.setBottomMargin(bottom: Int) {
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        val p = layoutParams as ViewGroup.MarginLayoutParams
        p.bottomMargin = dpToPx(top)
        requestLayout()
    }
}

fun View.getMarginLayoutParams(): ViewGroup.MarginLayoutParams? {
    return if (layoutParams is ViewGroup.MarginLayoutParams) {
        layoutParams as ViewGroup.MarginLayoutParams
    } else {
        null
    }

}

fun Activity.customToast(message: String) {
    val toastLayout: View = LayoutInflater.from(this).inflate(R.layout.custom_toast, null, false)

    val textView = toastLayout.findViewById(R.id.text_toast) as TextView
    textView.text = message

    val toast = Toast(this)
    toast.duration = Toast.LENGTH_SHORT
    toast.view = toastLayout
    toast.setGravity(Gravity.BOTTOM, 0, 150)
    toast.show()
}

infix fun View.onClick(function: (View) -> Unit) {
    setOnClickListener {
        function.invoke(it)
    }
}
