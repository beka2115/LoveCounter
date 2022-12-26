package com.example.lovecounter2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.lovecounter2.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTryAgain.setOnClickListener {
            findNavController().navigateUp()
        }
        getAndSetData()


    }

    private fun getAndSetData() {
        arguments?.let {
            val percentage = it.getString(CalculateFragment.KEY_FOR_PERCENTAGE)
            val result = it.getString(CalculateFragment.KEY_FOR_RESULT)
            val firstName = it.getString(CalculateFragment.KEY_FOR_FIRST_NAME)
            val secondName = it.getString(CalculateFragment.KEY_FOR_SECOND_NAME)
            with(binding){
                percentage?.let { it1 -> checkData(it1,percentageText) }
                result?.let { it1 -> checkData(it1,resultText) }
                firstName?.let { it1 -> checkData(it1,binding.firstName) }
                secondName?.let { it1 -> checkData(it1,binding.secondName) }
            }
        }
    }

    fun checkData(data:String, view: TextView){
        view.text=data
    }


}