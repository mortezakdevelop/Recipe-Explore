package com.example.recipeexplore.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.recipeexplore.models.register.recipe.ApodResponse

class ApodDiffCallback : DiffUtil.ItemCallback<ApodResponse>() {
    override fun areItemsTheSame(oldItem: ApodResponse, newItem: ApodResponse): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: ApodResponse, newItem: ApodResponse): Boolean {
        return oldItem == newItem
    }
}