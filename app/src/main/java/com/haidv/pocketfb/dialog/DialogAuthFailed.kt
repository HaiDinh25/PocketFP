package com.haidv.pocketfb.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.haidv.pocketfb.R

class DialogAuthFailed(context: Context, private val title: String?, private val content: String?, private val listener: OnCloseListener): Dialog(context) {

    private var buttonClose: TextView? = null
    private var textViewTitle: TextView? = null
    private var textViewContent: TextView? = null

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_auth_failed)

        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.attributes.windowAnimations = R.style.DialogBotToTopAnim

        buttonClose = findViewById(R.id.buttonClose)
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewContent = findViewById(R.id.textViewContent)

        textViewTitle?.text = title
        textViewContent?.text = content

        buttonClose?.setOnClickListener {
            dismiss()
            listener.onCloseListener()
        }
    }

    fun hideTitle() {
        textViewTitle?.visibility = View.GONE
    }

    interface OnCloseListener {
        fun onCloseListener()
    }

}