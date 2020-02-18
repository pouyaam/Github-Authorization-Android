package  com.mydigipay.challenge.extentions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.TypedValue
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mydigipay.challenge.dialogfragment.MessageDialogFragment
import com.mydigipay.challenge.dialogfragment.MessageDialogFragment.Companion.NETWORK_ERROR_DIALOG_TAG
import com.mydigipay.challenge.github.R


fun Activity.alert(
    message: String,
    title: String? = null,
    positive: String? = null,
    onPositive: (() -> Unit)? = null,
    negative: String? = null,
    onNegative: (() -> Unit)? = null,
    autoDismiss: Boolean = true
) {
    AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert)
        .setMessage(message)
        .apply {
            positive ?: return@apply
            setPositiveButton(positive) { dialog, which ->
                if (autoDismiss) dialog.dismiss()
                onPositive?.invoke()
            }
        }
        .apply {
            negative ?: return@apply
            setNegativeButton(negative) { dialog, which ->
                if (autoDismiss) dialog.dismiss()
                onNegative?.invoke()
            }
        }
        .setTitle(title)
        .create()
        .apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
        .show()
}

@Synchronized
fun AppCompatActivity.messageDialog(
    message: MessageDialogFragment.Message,
    tag: String? = null,
    cancelable: Boolean = true,
    onclick: (() -> Unit)? = null,
    onCancel: (() -> Unit)? = null
) = MessageDialogFragment.newInstance(message, cancelable, onclick, onCancel)
    .show(supportFragmentManager, tag ?: "MessageDialog")

fun AppCompatActivity.messageDialog(
    message: String,
    buttonText: String? = null,
    image: Int? = null,
    tag: String? = null,
    cancelable: Boolean = true,
    onClick: (() -> Unit)? = null,
    onCancel: (() -> Unit)? = null
) = messageDialog(
    MessageDialogFragment.Message(message, buttonText, image),
    tag,
    cancelable,
    onClick,
    onCancel
)

fun AppCompatActivity.networkErrorDialog(
    message: String,
    onRetry: (() -> Unit)? = null,
    onCancel: (() -> Unit)? = null
) {
    messageDialog(
        message,
        getString(R.string.retry),
        null,
        NETWORK_ERROR_DIALOG_TAG,
        true,
        onRetry, onCancel

    )
}


fun Activity.openUrlInBrowser(url: String) {
    val openURL = Intent(Intent.ACTION_VIEW)
    openURL.data = Uri.parse(url)
    startActivity(openURL)
}

fun Activity.getStatusBarHeight(): Int {
    var result = 0
    val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun Activity.getActionBarHeight(): Int {
    val tv = TypedValue()
    if (theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
        return TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
    }
    return 100
}