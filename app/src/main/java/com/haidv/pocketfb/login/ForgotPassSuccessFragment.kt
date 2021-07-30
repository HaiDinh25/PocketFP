package com.haidv.pocketfb.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.haidv.pocketfb.R
import com.haidv.pocketfb.databinding.FragmentForgotPassSuccessBinding

class ForgotPassSuccessFragment : Fragment() {

    private var binding: FragmentForgotPassSuccessBinding? = null

    companion object {
        @JvmStatic
        fun newInstance() =
            ForgotPassSuccessFragment().apply {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_pass_success, container, false)

        initUI()
        return binding?.root
    }

    private fun initUI() {
        binding?.let {
            it.buttonGoToHome.setOnClickListener {
                val controller = findNavController()
                controller.navigate(R.id.selectMethodLoginFragment)
            }
        }
    }

}