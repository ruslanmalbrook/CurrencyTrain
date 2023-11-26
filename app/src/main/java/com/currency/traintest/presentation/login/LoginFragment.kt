package com.currency.traintest.presentation.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.currency.traintest.MainActivity
import com.currency.traintest.R
import com.currency.traintest.databinding.FragmentLoginBinding
import com.currency.traintest.presentation.stat.StatFragment

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var authStateSignIn: Boolean = true

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            LoginViewModelFactory(requireContext().applicationContext)
        )[LoginViewModel::class.java]

        if (viewModel.isUserEntered())
            (requireActivity() as MainActivity).replaceFragment(StatFragment.newInstance())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.authSwitch.setOnClickListener {
            if (authStateSignIn) {
                binding.authSwitch.setText(R.string.i_have_an_account)
                binding.signInContainer.visibility = View.GONE
                binding.signUpContainer.visibility = View.VISIBLE
                binding.submit.text = getString(R.string.sign_up)
            } else {
                binding.authSwitch.setText(R.string.i_don_t_have_an_account_yet)
                binding.signInContainer.visibility = View.VISIBLE
                binding.signUpContainer.visibility = View.GONE
                binding.submit.text = getText(R.string.sign_in)
            }

            authStateSignIn = !authStateSignIn
        }

        binding.submit.setOnClickListener {
            if (authStateSignIn) {
                if (binding.login.text.length < 8)
                    binding.error.text = "size of login field must be 8 or more signs"
                else if (!viewModel.isValidEmail(binding.login.text))
                    binding.error.text = "email is not valid"
                else if (binding.password.text.length < 6)
                    binding.error.text = "size of password field must be 6 or more signs"
                else if (!viewModel.isUserExist(binding.login.text.toString()))
                    binding.error.text = "user with this login doesn't exists"
                else if (viewModel.checkUserPassword(
                        binding.login.text.toString(),
                        binding.password.text.toString())
                ) {
                    binding.error.text = "incorrect password"
                } else
                    (requireActivity() as MainActivity).replaceFragment(StatFragment.newInstance())
            } else {
                if (binding.name.text.length < 6)
                    binding.error.text = "size of name field must be 6 or more signs"
                else if (binding.email.text.length < 8)
                    binding.error.text = "size of email field must be 8 or more signs"
                else if (!viewModel.isValidEmail(binding.email.text))
                    binding.error.text = "email is not valid"
                else if (binding.password.text.length < 6)
                    binding.error.text = "size of password field must be 6 or more signs"
                else {
                    viewModel.addNewUser(binding.email.text.toString(), binding.password.text.toString())

                    Toast.makeText(requireContext(), "Welcome " + binding.name.text + "!", Toast.LENGTH_LONG).show()

                    (requireActivity() as MainActivity).replaceFragment(StatFragment.newInstance())
                }
            }
        }
    }

}