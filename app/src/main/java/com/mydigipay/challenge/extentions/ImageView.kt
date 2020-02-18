package  com.mydigipay.challenge.extentions

import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

/**
 * This will load image urls with Picasso.
 * TODO: We should consider the widthOfImageView and based on that create a path to load image.
 * Server provides multiple image sizes for each image, and we should use the one that's most appropriate
 * Talk with back-end team to get more info about this.
 */
fun ImageView.load(path: String?, @DrawableRes placeholder: Int = -1,callBack:(()->Unit)?=null) {
    var fullPath = path

    if (path.isNullOrBlank()) {
        return
    }

    if (!path.startsWith("http", true))
        fullPath = "file://$path"

    val picasso = Picasso.get().load(fullPath)
    if (placeholder != -1)
        picasso.placeholder(placeholder)

    picasso.into(this, object : Callback {
        override fun onSuccess() {
            Log.i("onSuccess: %s", fullPath)
            callBack?.invoke()

        }

        override fun onError(e: java.lang.Exception?) {
            Log.i("onError: %s", fullPath)
            e?.printStackTrace()
        }
    })
}


fun ImageView.loadWithCircularCrop(path: String?, @DrawableRes placeholder: Int? = null) {
    this.scaleType = ImageView.ScaleType.CENTER_CROP

    if (path.isNullOrBlank()) {
        return
    }
    var fullPath = path

    if (!path.startsWith("http", true))
        fullPath = "file://$path"

    val picasso = Picasso.get().load(fullPath)
        .apply { placeholder?.let { placeholder(it) } }
        .transform(CropCircleTransformation())
    try {
        picasso.into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

