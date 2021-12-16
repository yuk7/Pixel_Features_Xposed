package com.github.yuk7.xposed.pixelizerx.data.initdata.devices

class PixelFeatures {
    companion object {
        const val PIXEL_2016 = 0
        const val PIXEL_2017 = 1
        const val PIXEL_2018 = 2
        const val PIXEL_2019MID = 3
        const val PIXEL_2019 = 4
        const val PIXEL_2020MID = 5
        const val PIXEL_2020 = 6
        const val PIXEL_2021MID = 7
        const val PIXEL_2021 = 8

        private val featuresList = listOf<List<String>>(
            listOf(
                // PIXEL_2016
                "com.google.android.feature.GOOGLE_BUILD",
                "com.google.android.feature.GOOGLE_EXPERIENCE",
                "com.google.android.apps.photos.NEXUS_PRELOAD",
                "com.google.android.apps.photos.nexus_preload",
                "com.google.android.feature.PIXEL_EXPERIENCE",
                "com.google.android.apps.photos.PIXEL_PRELOAD",
                "com.google.android.apps.photos.PIXEL_2016_PRELOAD"
            ),
            listOf(
                // PIXEL_2017
                "com.google.android.feature.PIXEL_2017_EXPERIENCE",
                "com.google.android.apps.photos.PIXEL_2017_PRELOAD"
            ),
            listOf(
                // PIXEL_2018
                "com.google.android.feature.PIXEL_2018_EXPERIENCE",
                "com.google.android.apps.photos.PIXEL_2018_PRELOAD"
            ),
            listOf(
                // PIXEL_2019MID
                "com.google.android.feature.PIXEL_2019_MIDYEAR_EXPERIENCE",
                "com.google.android.apps.photos.PIXEL_2019_MIDYEAR_PRELOAD"
            ),
            listOf(
                // PIXEL_2019
                "com.google.android.feature.PIXEL_2019_EXPERIENCE",
                "com.google.android.apps.photos.PIXEL_2019_PRELOAD"
            ),
            listOf(
                // PIXEL_2020MID
                "com.google.android.feature.PIXEL_2020_MIDYEAR_EXPERIENCE",
                "com.google.android.apps.photos.PIXEL_2020_MIDYEAR_PRELOAD"
            ),
            listOf(
                // PIXEL_2020
                "com.google.android.feature.PIXEL_2020_EXPERIENCE",
                "com.google.android.apps.photos.PIXEL_2020_PRELOAD"
            ),
            listOf(
                // PIXEL_2021MID
                "com.google.android.feature.PIXEL_2021_MIDYEAR_EXPERIENCE",
                "com.google.android.apps.photos.PIXEL_2021_MIDYEAR_PRELOAD"
            ),
            listOf(
                // PIXEL_2021
                "com.google.android.feature.PIXEL_2021_EXPERIENCE",
                "com.google.android.apps.photos.PIXEL_2021_PRELOAD"
            )
        )

        fun generateFeaturesWhiteList(devicePeriod: Int): List<String> {
            val subList = featuresList.subList(0, devicePeriod + 1)
            return subList.flatten().distinct()
        }

        fun generateFeaturesBlackList(devicePeriod: Int): List<String> {
            val allFeatures = featuresList.flatten().distinct()
            return allFeatures - generateFeaturesWhiteList(devicePeriod)
        }

    }
}