package  com.mydigipay.challenge.dialogfragment

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Parcelable
import android.util.DisplayMetrics
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.mydigipay.challenge.github.R
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.dialog_message.*
import java.util.*


class MessageDialogFragment(
    val message: Message,
    val cancelable: Boolean = true,
    var onclick: (() -> Unit)? = null,
    var onCancel: (() -> Unit)? = null
) : DialogFragment() {

    companion object {
        const val NETWORK_ERROR_DIALOG_TAG = "NetworkError"
        val singleInstanceTags by lazy { listOf(NETWORK_ERROR_DIALOG_TAG) }
        val onClicks by lazy { LinkedList<() -> Unit>() }
        val onCancels by lazy { LinkedList<() -> Unit>() }
        var isShowing = false
        fun newInstance(
            message: Message,
            cancelable: Boolean = true,
            onclick: (() -> Unit)? = null,
            onCancel: (() -> Unit)? = null

        ) = MessageDialogFragment(
            message,
            cancelable,
            onclick,
            onCancel
        )
    }

    var accepted = false
    override fun show(manager: FragmentManager, tag: String?) {
        onclick?.let { onClicks.add(it) }
        onCancel?.let { onCancels.add(it) }
        if (singleInstanceTags.contains(tag) && isShowing)
            return
        super.show(manager, tag)
        isShowing = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_message, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        txtMessageDialogMessage.text = message.message
        btnMessageDialogAccept.text = message.buttonText ?: getString(R.string.accept)
        btnMessageDialogAccept.setOnClickListener {
            accepted = true
            notifyClick()
            dismiss()
        }
        isCancelable = cancelable
        if (!cancelable) {
            btnMessageDialogCancel.visibility = View.GONE
        } else
            btnMessageDialogCancel.setOnClickListener {
                dismiss()
            }
        message.imageResource?.takeIf { it != 0 }?.let {
            imgMessageDialogLogo.setImageResource(it)
        } ?: run { imgMessageDialogLogo.visibility = View.GONE }
    }

    private fun notifyClick() {
        onCancels.clear()
        onCancel = null
        if (singleInstanceTags.contains(tag))
            while (onClicks.isNotEmpty())
                onClicks.pop().invoke()
        else
            onclick?.invoke()
    }

    private fun notifyCancel() {
        onClicks.clear()
        onclick = null
        if (singleInstanceTags.contains(tag))
            while (onCancels.isNotEmpty())
                onCancels.pop().invoke()
        else
            onCancel?.invoke()
    }

    override fun onActivityCreated(arg0: Bundle?) {
        super.onActivityCreated(arg0)
        val metrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
        val window = dialog!!.window!!
        window.setGravity(Gravity.CENTER)
        window.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.attributes.windowAnimations = R.style.Bottomsheet_Animation
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun onDismiss(dialog: DialogInterface) {
        isShowing = false
        super.onDismiss(dialog)
        if (!accepted)
            notifyCancel()
    }

    @Parcelize
    data class Message(
        val message: String,
        val buttonText: String? = null,
        val imageResource: Int? = null
    ) : Parcelable
}