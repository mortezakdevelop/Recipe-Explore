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
    ): View? {
        binding = FragmentRecipeBinding.inflate(layoutInflater, container, false)
        adapter = PopularAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            showUsername()
        }

        val queries = mapOf("query" to "chicken", "number" to "10")

        binding.viewPager.adapter = adapter

        viewModel.apodList.observe(viewLifecycleOwner) {apodResponse ->
            val infiniteApodList = createInfiniteList(apodResponse)

            adapter.submitList(infiniteApodList)
            setupTabLayoutWithDots(apodResponse.size)
            Toast.makeText(requireContext(), apodResponse.size, Toast.LENGTH_SHORT).show()
            Log.d("recipeFragment", "onViewCreated: ${apodResponse.size}")

        }

        viewModel.fetchApod()


        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                // اگر کاربر به اولین آیتم فیک رسید، به انتهای لیست منتقل بشه
                if (position == 0) {
                    binding.viewPager.setCurrentItem(adapter.itemCount - 2, false)
                }
                // اگر کاربر به آخرین آیتم فیک رسید، به ابتدای لیست منتقل بشه
                else if (position == adapter.itemCount - 1) {
                    binding.viewPager.setCurrentItem(1, false)
                }
            }
        })

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun createInfiniteList(originalList: List<ApodResponse>): List<ApodResponse> {
        val infiniteList = mutableListOf<ApodResponse>()

        // اضافه کردن آخرین آیتم به ابتدای لیست
        infiniteList.add(originalList.last())

        // اضافه کردن آیتم‌های اصلی
        infiniteList.addAll(originalList)

        // اضافه کردن اولین آیتم به انتهای لیست
        infiniteList.add(originalList.first())

        return infiniteList
    }


    private fun setupTabLayoutWithDots(itemCount: Int) {
        binding.tabLayout.removeAllTabs() // پاک کردن تب‌های قبلی

        // ایجاد TabLayoutMediator و attach کردن
        tabLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // اینجا نیازی به تنظیم چیزی نیست
        }.also {
            it.attach() // attach کردن TabLayoutMediator
        }

        // تنظیم تعداد Dotها بر اساس لیست اصلی (بدون آیتم‌های فیک)
        for (i in 0 until itemCount) {
            binding.tabLayout.addTab(binding.tabLayout.newTab())
        }
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