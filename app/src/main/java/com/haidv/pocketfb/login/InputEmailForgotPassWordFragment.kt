package com.haidv.pocketfb.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.haidv.pocketfb.R
import com.haidv.pocketfb.databinding.FragmentForgotPassWordBinding
import com.haidv.pocketfb.databinding.FragmentInputEmailForgotPassWordBinding
import com.haidv.pocketfb.dialog.DialogAuthFailed

class InputEmailForgotPassWordFragment : Fragment() {

    private var binding: FragmentInputEmailForgotPassWordBinding? = null

    companion object {
        @JvmStatic
        fun newInstance() =
            InputEmailForgotPassWordFragment().apply {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_input_email_forgot_pass_word, container, false)

        initUI()
        return binding?.root
    }

    private fun initUI() {
        binding?.buttonForgotPass?.setOnClickListener {
            /*
            * if email unregistered -> show dialog
            *
            * */
            unRegisterEmail("登録されていないメールアドレスです")

            /*
            * else next screen
            * */
            val hashMap = HashMap<String, String>()
            hashMap["isLogin"] = true.toString()

            val bundle = bundleOf("hashMap" to hashMap)
            val controller = findNavController()
            controller.navigate(R.id.checkEmailRegisterFragment, bundle)
        }
    }

    fun unRegisterEmail(content: String) {
        val dialog = DialogAuthFailed(requireContext(), null, content, object : DialogAuthFailed.OnCloseListener {
            override fun onCloseListener() {

            }
        })
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.show()
        dialog.hideTitle()
    }

}