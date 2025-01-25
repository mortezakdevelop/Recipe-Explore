package com.example.recipeexplore.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.example.recipeexplore.R
import com.example.recipeexplore.databinding.ItemPopularViewPagerBinding
import com.example.recipeexplore.models.register.recipe.ApodResponse
import com.example.recipeexplore.models.register.recipe.ResponseRecipes
import com.example.recipeexplore.urils.BaseDiffUtils
import com.example.recipeexplore.urils.Constants
import javax.inject.Inject

class PopularAdapter : ListAdapter<ApodResponse, PopularAdapter.ApodViewHolder>(ApodDiffCallback()) {

    inner class ApodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.popularImg)

        fun bind(apod: ApodResponse) {
            imageView.load(apod.url) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_view_pager, parent, false)
        return ApodViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}






/*
class PopularAdapter @Inject constructor(private val recipes: List<ResponseRecipes.Result>) : RecyclerView.Adapter<PopularAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(private val binding: ItemPopularViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: ResponseRecipes.Result) {
            binding.apply {
                popularNameTxt.text = recipe.title
                popularImg.load(recipe.image) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemPopularViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size
}*/
