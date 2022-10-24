package com.example.testproject.base

import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.testproject.utils.extension.isNotNull
import com.example.testproject.utils.extension.isNull
import com.example.testproject.utils.extension.setColorFilter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.testproject.R

inline fun <reified T> ImageView.loadImage(
    source: T,
    @DrawableRes errorImage: Int? = null,
    widths: Int? = 0,
    heights: Int? = 0
) {
    var circularProgressDrawable: CircularProgressDrawable? = null
    if (errorImage.isNull()) {
        try {
            circularProgressDrawable = CircularProgressDrawable(this.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.setColorFilter(ContextCompat.getColor(context,R.color.purple_200))
            circularProgressDrawable.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    when (T::class) {
        String::class -> {
            val image = source.run {
                if ((this is String)) this else ""
            }
            Glide.with(this.context)
                .load(image)
                .apply(requestOptions(errorImage, circularProgressDrawable, widths, heights))
                .into(this)

        }
        Uri::class -> Glide.with(this.context)
            .load(source as Uri)
            .apply(requestOptions(errorImage, circularProgressDrawable, widths, heights))
            .into(this)
        else -> Glide.with(this.context)
            .load(source)
            .apply(requestOptions(errorImage, circularProgressDrawable, widths, heights))
            .into(this)
    }

}

fun requestOptions(
    errorImage: Int?,
    circularProgressDrawable: CircularProgressDrawable?, widths: Int? = 0, heights: Int? = 0
): RequestOptions {
    return if ((widths.isNull() && widths == 0) || (heights.isNull() && heights == 0))
        when {
            errorImage.isNotNull() -> {
                RequestOptions()
                    .useUnlimitedSourceGeneratorsPool(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(errorImage!!)
            }
            circularProgressDrawable.isNotNull() -> {
                RequestOptions()
                    .useUnlimitedSourceGeneratorsPool(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(circularProgressDrawable!!)
            }
            else -> {
                RequestOptions()
                    .useUnlimitedSourceGeneratorsPool(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            }
        }
    else {
        requestOptionsWithOverride(errorImage, circularProgressDrawable, widths!!, heights!!)
    }
}

fun requestOptionsWithOverride(
    errorImage: Int?,
    circularProgressDrawable: CircularProgressDrawable?, widths: Int = 0, heights: Int = 0
): RequestOptions {
    when {
        errorImage.isNotNull() -> {
            return RequestOptions()
                .useUnlimitedSourceGeneratorsPool(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(errorImage!!)
                .override(widths, heights)
        }
        circularProgressDrawable.isNotNull() -> {
            return RequestOptions()
                .useUnlimitedSourceGeneratorsPool(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(circularProgressDrawable!!)
                .override(widths, heights)
        }
        else -> {
            return RequestOptions()
                .useUnlimitedSourceGeneratorsPool(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(widths, heights)
        }
    }
}


/*
inline fun palletListener(view: ImageView, crossinline body: (Palette) -> Unit): BitmapImageViewTarget {
    return object : BitmapImageViewTarget(view) {
        override fun onResourceReady(resource: Bitmap, transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?) {
            super.onResourceReady(resource, transition)
            Palette.from(resource).generate { palette: Palette ->
                body.invoke(palette)
            }
        }
    }
}

fun View.createBackgroundColorTransition(
    @ColorInt startColor: Int,
    @ColorInt endColor: Int
): Animator {
    return createColorAnimator(this, "backgroundColor", startColor, endColor)
}

fun TextView.createTextColorTransition(
    @ColorInt startColor: Int,
    @ColorInt endColor: Int
): Animator {
    return createColorAnimator(this, "textColor", startColor, endColor)
}

private fun createColorAnimator(
    target: Any,
    propertyName: String, @ColorInt startColor: Int, @ColorInt endColor: Int
): Animator {
    val animator: ObjectAnimator
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        animator = ObjectAnimator.ofArgb(target, propertyName, startColor, endColor)
    } else {
        animator = ObjectAnimator.ofInt(target, propertyName, startColor, endColor)
        animator.setEvaluator(ArgbEvaluator())
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        animator.interpolator = PathInterpolator(0.4f, 0f, 1f, 1f)
    }
    animator.duration = 1000
    return animator
}

var i = 0f

fun rotateImageLeft(activity: Activity, uri: Uri): Bitmap? {
    val matrix = Matrix()
    i -= 90f
    matrix.postRotate(i)
    return getRotatedBitmap(matrix, activity, uri)
}

fun rotateImageRight(activity: Activity, uri: Uri): Bitmap? {
    val matrix = Matrix()
    i += 90f
    matrix.preRotate(i)
    return getRotatedBitmap(matrix, activity, uri)
}

private fun getRotatedBitmap(
    matrix: Matrix,
    activity: Activity,
    uri: Uri
): Bitmap? {
    var rotated: Bitmap? = null
    try {
        val parcelFileDescriptor = activity.contentResolver?.openFileDescriptor(uri, "r")
        val fileDescriptor = parcelFileDescriptor?.fileDescriptor
        val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        Thread().run {
            rotated = Bitmap.createBitmap(
                image, 0, 0, image.width, image.height,
                matrix, true
            )
        }
        parcelFileDescriptor?.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return rotated
}

fun getImageUriFromBitmap(context: Context, bitmap: Bitmap): Uri {
    val bytes = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
    return Uri.parse(path.toString())
}
*/
