package com.haidv.pocketfb.register

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.haidv.pocketfb.R
import com.haidv.pocketfb.databinding.FragmentSelectMethodRegisterBinding
import com.haidv.pocketfb.dialog.DialogAuthFailed
import com.haidv.pocketfb.dialog.DialogAuthFailed.OnCloseListener
import com.haidv.pocketfb.utils.IOnBackPress

class SelectMethodRegisterFragment : Fragment(), IOnBackPress {

    private var binding: FragmentSelectMethodRegisterBinding? = null
    private var controller: NavController? = null

    companion object {
        @JvmStatic
        fun newInstance() =
            SelectMethodRegisterFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_select_method_register,
            container,
            false
        )

        initUI()
        return binding?.root
    }

    private fun initUI() {
        controller = findNavController()
        binding?.let {
            val spanString = SpannableString(getString(R.string.register_terms))
            val clickableSpan: ClickableSpan = object : ClickableSpan() {
                override fun onClick(textView: View) {
                    //open web view terms
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = true
                }
            }
            spanString.setSpan(clickableSpan, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            it.buttonTerms.text = spanString
            it.buttonTerms.movementMethod = LinkMovementMethod.getInstance()
            it.buttonTerms.highlightColor = ContextCompat.getColor(
                requireContext(),
                R.color.text_dark_blue
            )

            it.checkboxTerms.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    updateBackground(
                        it.buttonApple,
                        R.drawable.bg_button_register_check,
                        R.color.black
                    )
                    updateBackground(
                        it.buttonYahoo,
                        R.drawable.bg_button_register_check,
                        R.color.black
                    )
                    updateBackground(
                        it.buttonGoogle,
                        R.drawable.bg_button_register_check,
                        R.color.black
                    )
                    updateBackground(
                        it.buttonEmail,
                        R.drawable.bg_button_register_check,
                        R.color.black
                    )

                    val dialog = DialogAuthFailed(requireContext(),
                        getString(R.string.dialog_auth_title),
                        getString(
                            R.string.dialog_auth_content
                        ),
                        object : OnCloseListener {
                            override fun onCloseListener() {
                                controller?.navigate(R.id.inputInfoRegisterFragment)
                            }
                        })
                    dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
                    dialog.show()
                } else {
                    updateBackground(
                        it.buttonApple,
                        R.drawable.bg_button_register_not_check,
                        R.color.grey_300
                    )
                    updateBackground(
                        it.buttonYahoo,
                        R.drawable.bg_button_register_not_check,
                        R.color.grey_300
                    )
                    updateBackground(
                        it.buttonGoogle,
                        R.drawable.bg_button_register_not_check,
                        R.color.grey_300
                    )
                    updateBackground(
                        it.buttonEmail,
                        R.drawable.bg_button_register_not_check,
                        R.color.grey_300
                    )
                }
            }
            it.buttonLogin.setOnClickListener {
                controller?.navigate(R.id.selectMethodLoginFragment)
            }
        }
    }

    private fun updateBackground(button: TextView, background: Int, textColor: Int) {
        button.background = ContextCompat.getDrawable(requireContext(), background)
        button.setTextColor(ContextCompat.getColor(requireContext(), textColor))
    }

    override fun onBackPress() {

    }

}