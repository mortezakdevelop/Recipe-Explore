package com.example.recipeexplore.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import coil.load
import com.example.recipeexplore.R
import com.example.recipeexplore.databinding.FragmentRegisterBinding
import com.example.recipeexplore.models.register.RegisterRequest
import com.example.recipeexplore.urils.Constants
import com.example.recipeexplore.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    private val viewModel:RegisterViewModel by viewModels()

    @Inject
    lateinit var registerRequest: RegisterRequest
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivRegister.load(R.drawable.register_logo)

        binding.apply {
            etEmail.addTextChangedListener{
                if (it.toString().contains("@")){
                    tlEmail.error = ""
                }
                else{
                    tlEmail.error = getString(R.string.email_not_valid)
                }
            }

            btnRegister.setOnClickListener {
                val firstName = etFirstName.text.toString()
                val lastName = etLastName.text.toString()
                val userName = etUserName.text.toString()
                val email = etEmail.text.toString()

                registerRequest.firstName = firstName
                registerRequest.lastName = lastName
                registerRequest.username = userName
                registerRequest.email = email

                viewModel.callRegisterApi(Constants.API_KEY,registerRequest)
            }
        }
    }
}