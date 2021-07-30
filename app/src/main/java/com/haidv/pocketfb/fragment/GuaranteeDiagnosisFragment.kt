package com.haidv.pocketfb.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.haidv.pocketfb.R
import com.haidv.pocketfb.databinding.FragmentGuaranteeDiagnosisBinding

/*
* ピタッと保障診断 screen
* */

class GuaranteeDiagnosisFragment : Fragment() {

    private var binding: FragmentGuaranteeDiagnosisBinding? = null

    companion object {
        @JvmStatic
        fun newInstance() =
            GuaranteeDiagnosisFragment().apply {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_guarantee_diagnosis, container, false)

        initUI()
        return binding?.root
    }

    private fun initUI() {
        val controller = findNavController()
        binding?.let {
//            it.buttonBack.setOnClickListener {controller.navigate(R.id.aboutChildrenFragment)}
            it.buttonNext.setOnClickListener {controller.navigate(R.id.aboutSpouseFragment)}
        }
    }

}