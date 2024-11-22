package com.example.recipeexplore.ui.register

import android.net.NetworkRequest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.recipeexplore.R
import com.example.recipeexplore.databinding.FragmentRegisterBinding
import com.example.recipeexplore.models.register.RegisterRequest
import com.example.recipeexplore.urils.Constants
import com.example.recipeexplore.urils.NetworkChecker
import com.example.recipeexplore.urils.NetworkState
import com.example.recipeexplore.urils.showSnackBar
import com.example.recipeexplore.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: RegisterViewModel by viewModels()

    @Inject
    lateinit var registerRequest: RegisterRequest

    @Inject
    lateinit var networkChecker: NetworkChecker
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivRegister.load(R.drawable.register_logo)

        binding.apply {
            etEmail.addTextChangedListener {
                if (it.toString().contains("@")) {
                    tlEmail.error = ""
                } else {
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

                lifecycleScope.launch {
                    networkChecker.checkNetworkAvailability().collect() { state ->
                        if (state) {
                            viewModel.callRegisterApi(Constants.PROJECT_API_KEY, registerRequest)
                        } else {
                            root.showSnackBar(getString(R.string.checkConnection))
                        }
                    }
                }
            }
            loadRegisterData()
        }
    }

    private fun loadRegisterData() {
        viewModel.registerLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkState.Loading -> {

                }

                is NetworkState.Success -> {
                    response.data?.let { data ->
                        viewModel.saveRegisterUserData(data.username.toString(),data.hash.toString())
                    }
                }

                is NetworkState.Error -> {
                    response.message?.let { binding.root.showSnackBar(it) }
                }
            }
        }
    }
}