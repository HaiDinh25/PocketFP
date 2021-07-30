package com.haidv.pocketfb.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.haidv.pocketfb.R
import com.haidv.pocketfb.databinding.FragmentAboutSpouseBinding

/*
* 配偶者について screen
* */

class AboutSpouseFragment : Fragment() {

    private var binding: FragmentAboutSpouseBinding? = null

    companion object {
        @JvmStatic
        fun newInstance() =
            AboutSpouseFragment().apply {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_spouse, container, false)

        initUI()
        return binding?.root
    }

    private fun initUI() {
        val controller = findNavController()
        binding?.let {
            it.buttonBack.setOnClickListener {controller.navigate(R.id.guaranteeDiagnosisFragment)}
            it.buttonNext.setOnClickListener {controller.navigate(R.id.aboutChildrenFragment)}
        }
    }

}