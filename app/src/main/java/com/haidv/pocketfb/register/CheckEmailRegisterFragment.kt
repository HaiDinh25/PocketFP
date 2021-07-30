package com.haidv.pocketfb.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.haidv.pocketfb.R
import com.haidv.pocketfb.databinding.FragmentCheckEmailRegisterBinding

class CheckEmailRegisterFragment : Fragment() {

    private var binding: FragmentCheckEmailRegisterBinding? = null
    private var email: String? = null
    private var isLogin: String? = null

    companion object {
        @JvmStatic
        fun newInstance() =
            CheckEmailRegisterFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (arguments != null) {
                val info: HashMap<String, String> = arguments?.getSerializable("hashMap") as HashMap<String, String>
                email = info["email"]
                isLogin = info["isLogin"]
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_check_email_register, container, false)

        initUI()
        return binding?.root
    }

    private fun initUI() {
        binding?.let {
            it.textViewEmail.text = email
            it.buttonRegister.setOnClickListener {
                val controller = findNavController()
                if (isLogin.equals("true")) {
                    controller.navigate(R.id.forgotPassWordFragment)
                } else {
                    controller.navigate(R.id.registerSuccessFragment)
                }
            }
        }
    }

}