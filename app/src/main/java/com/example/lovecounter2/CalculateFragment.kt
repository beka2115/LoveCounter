package com.example.lovecounter2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lovecounter2.databinding.FragmentCalculateBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CalculateFragment : Fragment() {
    private lateinit var binding: FragmentCalculateBinding
    lateinit var percentageNum: String
    lateinit var result: String
    lateinit var firstName: String
    lateinit var secondName: String

    companion object {
        const val KEY_FOR_PERCENTAGE = "key1"
        const val KEY_FOR_RESULT = "key2"
        const val KEY_FOR_SECOND_NAME = "key3"
        const val KEY_FOR_FIRST_NAME = "key4"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicker()

    }

    private fun initClicker() {
        with(binding) {

            calculateBtn.setOnClickListener {
                if (firstNameEd.text.isNotEmpty() && secondNameEd.text.isNotEmpty()) {
                    RetrofitService().apishka.getLoveResult(
                        firstName = firstNameEd.text.toString(),
                        secondName = secondNameEd.text.toString()
                    ).enqueue(object : Callback<LoveModel> {

                        override fun onResponse(
                            call: Call<LoveModel>,
                            response: Response<LoveModel>
                        ) {
                            percentageNum = response.body()?.percentage.toString()
                            result = response.body()?.result.toString()
                            firstName = firstNameEd.text.toString()
                            secondName = secondNameEd.text.toString()
                            sendData()
                        }

                        override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                            Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                        }
                    })
                } else {
                    Toast.makeText(context, "Введите имя", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun sendData() {
        findNavController().navigate(
            R.id.resultFragment,
            bundleOf(
                KEY_FOR_PERCENTAGE to percentageNum, KEY_FOR_RESULT to result,
                KEY_FOR_FIRST_NAME to firstName, KEY_FOR_SECOND_NAME to secondName
            )
        )
    }


}