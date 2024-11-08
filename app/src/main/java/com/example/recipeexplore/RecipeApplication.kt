package com.example.recipeexplore

import android.app.Application
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump

class RecipeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        ViewPump.init(
            ViewPump.builder().addInterceptor(
                CalligraphyInterceptor(
                    CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/roboto.ttf")
                        .build()
                )
            ).build()
        )
    }
}