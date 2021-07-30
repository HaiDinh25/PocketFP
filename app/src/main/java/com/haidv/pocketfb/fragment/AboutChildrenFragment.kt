package com.haidv.pocketfb.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.haidv.pocketfb.R
import com.haidv.pocketfb.databinding.FragmentAboutChildrenBinding

/*
* 子どもについて screen
* */

class AboutChildrenFragment : Fragment() {

    private var binding: FragmentAboutChildrenBinding? = null

    companion object {
        @JvmStatic
        fun newInstance() =
            AboutChildrenFragment().apply {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_children, container, false)

        initUI()
        return binding?.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initUI() {
        val controller = findNavController()
        binding?.let {
            it.buttonBack.setOnClickListener {controller.navigate(R.id.aboutSpouseFragment)}
            it.buttonNext.setOnClickListener {controller.navigate(R.id.guaranteeDiagnosisFragment)}
            it.buttonOn.isChecked = true
            it.buttonOn.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    it.buttonOn.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_switch_on)
                    it.buttonOff.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_switch_off)
                }
            }
            it.buttonOff.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    it.buttonOn.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_switch_off)
                    it.buttonOff.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_switch_on)
                }
            }
        }
    }

}