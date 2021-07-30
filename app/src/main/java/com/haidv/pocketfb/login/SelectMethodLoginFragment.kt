package com.haidv.pocketfb.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.haidv.pocketfb.R
import com.haidv.pocketfb.databinding.FragmentSelectMethodLoginBinding
import com.haidv.pocketfb.dialog.DialogAuthFailed
import com.haidv.pocketfb.utils.IOnBackPress

class SelectMethodLoginFragment : Fragment(), IOnBackPress, View.OnClickListener {

    private var binding: FragmentSelectMethodLoginBinding? = null

    companion object {
        @JvmStatic
        fun newInstance() =
            SelectMethodLoginFragment().apply {
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
            R.layout.fragment_select_method_login,
            container,
            false
        )

        initUI()
        return binding?.root
    }

    private fun initUI() {
        binding?.let {
            val fm = activity?.supportFragmentManager
            for (i in 0 until fm!!.backStackEntryCount) {
                fm.popBackStack()
            }

            it.buttonRegister.setOnClickListener(this)
            it.buttonForgotPass.setOnClickListener(this)
            it.buttonLogin.setOnClickListener(this)
            it.buttonApple.setOnClickListener(this)
            it.buttonGoogle.setOnClickListener(this)
            it.buttonYahoo.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        val controller = findNavController()
        when(v?.id) {
            R.id.buttonRegister -> controller.navigate(R.id.selectMethodRegisterFragment)
            R.id.buttonForgotPass -> controller.navigate(R.id.inputEmailForgotPassWordFragment)
            R.id.buttonLogin -> {
                /*if login with email failed
                    loginWithEmailFailed() */
            }
            R.id.buttonApple -> loginOtherFailed("Appleで登録情報はありません。他の方法でログインをお試しください。")
            R.id.buttonGoogle -> loginOtherFailed("Googleで登録情報はありません。他の方法でログインをお試しください。")
            R.id.buttonYahoo -> loginOtherFailed("Yahooで登録情報はありません。他の方法でログインをお試しください。")
        }
    }

    fun loginWithEmailFailed() {
        binding?.let {
            it.editTextEmail.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.bg_button_red_border
            )
            it.editTextPass.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.bg_button_red_border
            )
            it.textViewLoginFailed.visibility = View.VISIBLE
        }
    }

    fun loginOtherFailed(content: String) {
        val dialog = DialogAuthFailed(
            requireContext(),
            null,
            content,
            object : DialogAuthFailed.OnCloseListener {
                override fun onCloseListener() {

                }
            })
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.show()
        dialog.hideTitle()
    }

    override fun onBackPress() {

    }

}