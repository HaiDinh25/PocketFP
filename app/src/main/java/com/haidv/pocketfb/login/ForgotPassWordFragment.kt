package com.haidv.pocketfb.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.haidv.pocketfb.R
import com.haidv.pocketfb.databinding.FragmentForgotPassWordBinding

class ForgotPassWordFragment : Fragment() {

    private var binding: FragmentForgotPassWordBinding? = null

    companion object {
        @JvmStatic
        fun newInstance() =
            ForgotPassWordFragment().apply {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_pass_word, container, false)

        initUI()
        return binding?.root
    }

    private fun initUI() {
        binding?.let {
            it.editTextNewPass.addTextChangedListener(object : TextWatcher {
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
                        it.textViewNewPassValid.visibility = View.VISIBLE
                        it.editTextNewPass.background = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.bg_button_red_border
                        )
                    } else {
                        it.textViewNewPassValid.visibility = View.GONE
                        it.editTextNewPass.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_gray_border)
                    }
                }
            })

            it.editTextConfirmPass.addTextChangedListener(object : TextWatcher {
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
                        it.textViewConfirmPassValid.visibility = View.VISIBLE
                        it.editTextConfirmPass.background = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.bg_button_red_border
                        )
                    } else if (s.length >= 6) {
                        it.textViewConfirmPassValid.visibility = View.GONE
                        if (s.toString() != it.editTextNewPass.text.toString()) {
                            it.textViewNotMatch.visibility = View.VISIBLE
                            it.editTextConfirmPass.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_button_red_border)
                        } else {
                            it.textViewNotMatch.visibility = View.GONE
                            it.editTextConfirmPass.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_gray_border)
                        }
                    } else {
                        it.textViewNotMatch.visibility = View.GONE
                        it.editTextConfirmPass.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_gray_border)
                    }
                }
            })
            it.buttonForgotPass.setOnClickListener {
                val controller = findNavController()
                controller.navigate(R.id.forgotPassSuccessFragment)
            }
        }

    }

}