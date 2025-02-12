package com.example.recipeexplore.models.register.recipe


import com.google.gson.annotations.SerializedName

data class ApodResponse(
    @SerializedName("copyright")
    val copyright: String?, // Jeff Schilling
    @SerializedName("date")
    val date: String?, // 2025-01-25
    @SerializedName("explanation")
    val explanation: String?, // Clouds of stardust drift through this deep skyscape, across the Perseus molecular cloud some 850 light-years away. Dusty nebulae reflecting light from embedded young stars stand out in the nearly 4 degree wide field of view. With a characteristic bluish color reflection nebula NGC 1333 is prominent near center. Hints of contrasting red emission from Herbig-Haro objects, the jets and shocked glowing gas emanating from recently formed stars, are scattered across the dusty expanse. While many stars are forming in the molecular cloud, most are obscured at visible wavelengths by the pervasive dust. The chaotic environment surrounding NGC 1333 may be similar to one in which our own Sun formed over 4.5 billion years ago. At the estimated distance of the Perseus molecular cloud, this cosmic scene would span about 80 light-years.   Growing Gallery: Comet ATLAS (G3)
    @SerializedName("hdurl")
    val hdurl: String?, // https://apod.nasa.gov/apod/image/2501/ngc1333_jeff_version_4_2048.jpg
    @SerializedName("media_type")
    val mediaType: String?, // image
    @SerializedName("service_version")
    val serviceVersion: String?, // v1
    @SerializedName("title")
    val title: String?, // Stardust in the Perseus Molecular Cloud
    @SerializedName("url")
    val url: String? // https://apod.nasa.gov/apod/image/2501/ngc1333_jeff_version_4_1024.jpg
)