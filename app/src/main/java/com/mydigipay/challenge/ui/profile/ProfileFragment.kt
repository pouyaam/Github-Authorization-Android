package com.mydigipay.challenge.ui.profile

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mydigipay.challenge.extentions.inputDialog
import com.mydigipay.challenge.extentions.loadWithCircularCrop
import com.mydigipay.challenge.github.R
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Exception

class ProfileFragment : Fragment(), View.OnClickListener {


    private val viewModel by viewModel<ProfileViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imgEditName.setOnClickListener(this)
        imgEditCompany.setOnClickListener(this)
        imgEditBlog.setOnClickListener(this)
        imgEditEmail.setOnClickListener(this)
        imgEditLocation.setOnClickListener(this)
        imgEditBio.setOnClickListener(this)
        viewModel.profileState.observe(viewLifecycleOwner, Observer { state ->
            imgProfileAvatar.loadWithCircularCrop(state.user.avatarUrl)
            txtProfileName.titledText = state.user.name
            txtProfileCompany.titledText = state.user.company
            txtProfileBlog.titledText = state.user.blog
            txtProfileEmail.titledText = state.user.email
            txtProfileLocation.titledText = state.user.location
            txtProfileBio.titledText = state.user.bio
        })


    }

    private var TextView.titledText: String?
        get() = try {
            text.split(":")[1].trim()
        } catch (e: Exception) {
            ""
        }
        set(value) {
            text =
                HtmlCompat.fromHtml("<b>$tag</b>: ${value ?: ""}", HtmlCompat.FROM_HTML_MODE_LEGACY)
        }


    override fun onClick(v: View?) {
        context!!.inputDialog(v!!.tag.toString()) { key, value ->
            viewModel.requestUpdate(key, value)
        }
    }
}