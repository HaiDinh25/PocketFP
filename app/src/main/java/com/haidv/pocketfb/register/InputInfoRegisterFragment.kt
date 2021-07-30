package com.haidv.pocketfb.register

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.haidv.pocketfb.R
import com.haidv.pocketfb.databinding.FragmentInputInfoRegisterBinding
import com.haidv.pocketfb.dialog.DialogAuthFailed

class InputInfoRegisterFragment : Fragment() {

    private var binding: FragmentInputInfoRegisterBinding? = null
    private var email: String = ""

    companion object {
        @JvmStatic
        fun newInstance() =
            InputInfoRegisterFragment().apply {
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
            R.layout.fragment_input_info_register,
            container,
            false
        )

        initUI()
        return binding?.root
    }

    private fun initUI() {
        binding?.let {
            it.editTextEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    it.editTextEmail.filters = arrayOf(filter)
                }

                override fun afterTextChanged(s: Editable?) {
                    email = it.editTextEmail.text.toString()
                    if (!validEmail(email)) {
                        it.textViewEmailValid.visibility = View.VISIBLE
                    } else {
                        it.textViewEmailValid.visibility = View.GONE
                    }
                    email = it.editTextEmail.text.toString()
                }
            })

            it.editTextPass.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s.isNullOrEmpty() || s.length < 6) {
                        it.textViewPassValid.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.red_new
                            )
                        )
                    } else {
                        it.textViewPassValid.visibility = View.GONE
                    }
                }
            })

            it.buttonRegister.setOnClickListener {
                val dialog = DialogAuthFailed(
                    requireContext(),
                    null,
                    getString(R.string.dialog_email_registered),
                    object : DialogAuthFailed.OnCloseListener {
                        override fun onCloseListener() {
                            val hashMap = HashMap<String, String>()
                            hashMap["email"] = email
                            hashMap["isLogin"] = false.toString()

                            val controller = findNavController()
                            val bundle = bundleOf("hashMap" to hashMap)
                            controller.navigate(R.id.checkEmailRegisterFragment, bundle)
                        }
                    })
                dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
                dialog.show()
                dialog.hideTitle()
            }
        }
    }

    fun validEmail(email: String?): Boolean {
        val emailPattern = "^[_A-Za-z0-9]+(\\.[_A-Za-z0-9]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,150})\$"
        return email.toString().trim { it <= ' ' }.matches(emailPattern.toRegex())
    }

    //remove space
    var filter = InputFilter { source, start, end, _, _, _ ->
        for (i in start until end) {
            if (Character.isWhitespace(source[i])) {
                return@InputFilter ""
            }
        }
        null
    }

}