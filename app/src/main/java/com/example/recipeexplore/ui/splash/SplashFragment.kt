package com.example.recipeexplore.ui.splash

import android.os.Build.VERSION_CODES
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.recipeexplore.R
import com.example.recipeexplore.databinding.FragmentSplashBinding
import com.example.recipeexplore.models.register.RegisterRequest
import com.example.recipeexplore.urils.Constants
import com.example.recipeexplore.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding:FragmentSplashBinding
    private val viewModel : RegisterViewModel by viewModels()

    @Inject
    lateinit var registerRequest: RegisterRequest
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            bgSplash.load(R.drawable.bg_splash)

            viewModel.callRegisterApi(Constants.PROJECT_API_KEY, registerRequest)

            lifecycleScope.launch {
                delay(2500)
                viewModel.readRegisterUserData.asLiveData().observe(viewLifecycleOwner){

                    findNavController().popBackStack(R.id.splashFragment, true)

                    if (it.username.isNotEmpty()){
                        findNavController().navigate(R.id.action_splashFragment_to_recipeFragment)
                    }else{
                        findNavController().navigate(R.id.action_splashFragment_to_registerFragment)
                    }
                }
            }
        }
    }
}