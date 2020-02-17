package com.mydigipay.challenge.extentions

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.utils.githubClinetId
import com.mydigipay.challenge.utils.githubClinetSecret
import kotlinx.android.synthetic.main.dialog_api_info.view.*

fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Context.showToast(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, resId, duration).show()
}

inline fun Context.inputDialog(
    tag: String,
    crossinline onConfirm: (key: String, value: String) -> Unit
) {
    val alertDialog = AlertDialog.Builder(this)
    alertDialog.setTitle("Enter your $tag")
    alertDialog.setMessage("")
    val input = EditText(this)
    input.tag = tag
    val lp = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT
    )
    alertDialog.setView(input.apply { layoutParams = lp })
    alertDialog.setPositiveButton("Save") { dialog, which ->
        onConfirm(tag, input.text.toString().trim())
    }
    alertDialog.setNegativeButton("Cancel") { dialog, which ->
        dialog.dismiss()
    }
    alertDialog.show()
}


inline fun Activity.githubApiDialog(crossinline onConfirm: (clientId: String, clientSecret: String) -> Unit): AlertDialog {
    val alertDialogBuilder = AlertDialog.Builder(this)
    alertDialogBuilder.setTitle(R.string.apiDialogTitle)
    alertDialogBuilder.setMessage(R.string.apiDialogMessage)
    val view = LayoutInflater.from(this).inflate(R.layout.dialog_api_info, null)
    view.txtClientId.text = githubClinetId
    view.txtClientSecret.text = githubClinetSecret
    alertDialogBuilder.setView(view)
    alertDialogBuilder.setPositiveButton(R.string.save, null)

    alertDialogBuilder.setCancelable(false)
    alertDialogBuilder.setNegativeButton(R.string.cancel) { dialog, which ->
        finish()
    }
    return alertDialogBuilder.show().also { alertDialog ->
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            if (view.edtClientId.text.toString().trim().isBlank() ||  view.edtClientSecret.text.toString().trim().isBlank()) {
                showToast(R.string.enterAllData)
                return@setOnClickListener
            }
            onConfirm(view.edtClientId.text.toString().trim(),  view.edtClientSecret.text.toString().trim())
            alertDialog.dismiss()
        }
    }
}