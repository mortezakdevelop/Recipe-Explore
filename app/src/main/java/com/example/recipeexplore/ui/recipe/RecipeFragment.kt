package com.example.recipeexplore.ui.recipe

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.recipeexplore.R
import com.example.recipeexplore.adapter.PopularAdapter
import com.example.recipeexplore.databinding.FragmentRecipeBinding
import com.example.recipeexplore.models.register.recipe.ApodResponse
import com.example.recipeexplore.viewmodel.RecipeViewModel
import com.example.recipeexplore.viewmodel.RegisterViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipeFragment : Fragment() {

    private val registerViewModel: RegisterViewModel by viewModels()
    private val viewModel:RecipeViewModel by viewModels()
    private lateinit var binding: FragmentRecipeBinding

    private lateinit var adapter: PopularAdapter
    private var tabLayoutMediator: TabLayoutMediator? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBinding.inflate(layoutInflater, container, false)
        adapter = PopularAdapter()


        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            showUsername()
        }
        binding.viewPager.adapter = adapter


    }



    @SuppressLint("SetTextI18n")
    suspend fun showUsername() {
        registerViewModel.readRegisterUserData.collect {

        }
    }

    private fun getEmojiByUnicode(): String {
        return String(Character.toChars(0x1f44b))
    }
}