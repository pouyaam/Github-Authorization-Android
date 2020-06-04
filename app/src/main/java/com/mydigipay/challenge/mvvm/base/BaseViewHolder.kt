package com.mydigipay.challenge.mvvm.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/3/2020.
 *
 * mahdiZTD@gmail.com
 */

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(position: Int)
}
