package com.haidv.pocketfb.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.haidv.pocketfb.R
import com.haidv.pocketfb.activity.MainActivity
import com.haidv.pocketfb.databinding.FragmentRegisterSuccessBinding

class RegisterSuccessFragment : Fragment() {

    private var binding: FragmentRegisterSuccessBinding? = null

    companion object {
        @JvmStatic
        fun newInstance() =
            RegisterSuccessFragment().apply {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_success, container, false)

        initUI()
        return binding?.root
    }

    private fun initUI() {
        binding?.let {
            it.buttonStart.setOnClickListener {
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }
    }

}