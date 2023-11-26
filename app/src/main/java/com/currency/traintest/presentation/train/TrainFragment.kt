package com.currency.traintest.presentation.train

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.currency.traintest.MainActivity
import com.currency.traintest.R
import com.currency.traintest.databinding.FragmentLoginBinding
import com.currency.traintest.databinding.FragmentTrainBinding
import com.currency.traintest.presentation.stat.StatFragment

class TrainFragment : Fragment() {

    private var _binding: FragmentTrainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = TrainFragment()
    }

    private lateinit var viewModel: TrainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TrainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submit.setOnClickListener {
            (requireActivity() as MainActivity).replaceFragment(StatFragment.newInstance())
        }
    }

}