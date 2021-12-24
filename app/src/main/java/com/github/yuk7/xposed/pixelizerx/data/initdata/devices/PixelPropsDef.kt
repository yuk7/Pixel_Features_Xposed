package com.github.yuk7.xposed.pixelizerx.data.initdata.devices

import com.github.yuk7.xposed.pixelizerx.data.db.emulatedevice.EmulateDevice

class PixelPropsDef {
    companion object {
        fun getAllInitialDeviceProps(): List<EmulateDevice> {
            val pixelXL_10 = EmulateDevice(
                "Pixel XL (Android 10)",
                mapOf(
                    Pair("BRAND", "google"),
                    Pair("MANUFACTURER", "Google"),
                    Pair("DEVICE", "marlin"),
                    Pair("BOARD", "marlin"),
                    Pair("PRODUCT", "marlin"),
                    Pair("MODEL", "Pixel XL"),
                    Pair(
                        "FINGERPRINT",
                        "google/marlin/marlin:10/QP1A.191005.007.A3/5972272:user/release-keys"
                    )
                ),
                PixelFeatures.generateFeaturesWhiteList(PixelFeatures.PIXEL_2016),
                PixelFeatures.generateFeaturesBlackList(PixelFeatures.PIXEL_2016)
            )
            val pixel2_10 = EmulateDevice(
                "Pixel 2 (Android 10)",
                pixelXL_10.build + mapOf(
                    Pair("DEVICE", "walleye"),
                    Pair("BOARD", "walleye"),
                    Pair("PRODUCT", "walleye"),
                    Pair("MODEL", "Pixel 2"),
                    Pair(
                        "FINGERPRINT",
                        "google/walleye/walleye:10/QQ1A.200205.002/6084386:user/release-keys"
                    )
                ),
                PixelFeatures.generateFeaturesWhiteList(PixelFeatures.PIXEL_2017),
                PixelFeatures.generateFeaturesBlackList(PixelFeatures.PIXEL_2017)
            )
            val pixel2_11 = EmulateDevice(
                "Pixel 2 (Android 11)",
                pixel2_10.build + mapOf(
                    Pair(
                        "FINGERPRINT",
                        "google/walleye/walleye:11/RP1A.201005.004/6782484:user/release-keys"
                    )
                ),
                PixelFeatures.generateFeaturesWhiteList(PixelFeatures.PIXEL_2017),
                PixelFeatures.generateFeaturesBlackList(PixelFeatures.PIXEL_2017)
            )

            val pixel3XL_11 = EmulateDevice(
                "Pixel 3 XL (Android 11)",
                pixelXL_10.build + mapOf(
                    Pair("DEVICE", "crosshatch"),
                    Pair("BOARD", "crosshatch"),
                    Pair("PRODUCT", "crosshatch"),
                    Pair("MODEL", "Pixel 3 XL"),
                    Pair(
                        "FINGERPRINT",
                        "google/crosshatch/crosshatch:11/RQ3A.210805.001.A1/7474174:user/release-keys"
                    )
                ),
                PixelFeatures.generateFeaturesWhiteList(PixelFeatures.PIXEL_2018),
                PixelFeatures.generateFeaturesWhiteList(PixelFeatures.PIXEL_2018)
            )

            val pixel3XL_12 = EmulateDevice(
                "Pixel 3 XL (Android 12)",
                pixel3XL_11.build + mapOf(
                    Pair(
                        "FINGERPRINT",
                        "google/crosshatch/crosshatch:12/SP1A.210812.015/7679548:user/release-keys"
                    )
                ),
                PixelFeatures.generateFeaturesWhiteList(PixelFeatures.PIXEL_2018),
                PixelFeatures.generateFeaturesWhiteList(PixelFeatures.PIXEL_2018)
            )

            val pixel5_11 = EmulateDevice(
                "Pixel 5 (Android 11)",
                pixelXL_10.build + mapOf(
                    Pair("DEVICE", "redfin"),
                    Pair("BOARD", "redfin"),
                    Pair("PRODUCT", "redfin"),
                    Pair("MODEL", "Pixel 5"),
                    Pair(
                        "FINGERPRINT",
                        "google/redfin/redfin:11/RQ3A.210905.001/7511028:user/release-keys"
                    )
                ),
                PixelFeatures.generateFeaturesWhiteList(PixelFeatures.PIXEL_2020),
                PixelFeatures.generateFeaturesBlackList(PixelFeatures.PIXEL_2020)
            )
            val pixel5_12 = EmulateDevice(
                "Pixel 5 (Android 12)",
                pixel5_11.build + mapOf(
                    Pair(
                        "FINGERPRINT",
                        "google/redfin/redfin:12/SQ1A.211205.008/7888514:user/release-keys"
                    )
                ),
                PixelFeatures.generateFeaturesWhiteList(PixelFeatures.PIXEL_2020),
                PixelFeatures.generateFeaturesBlackList(PixelFeatures.PIXEL_2020)
            )
            val pixel6_12 = EmulateDevice(
                "Pixel 6 Pro (Android 12)",
                pixel2_11.build + mapOf(
                    Pair("DEVICE", "raven"),
                    Pair("PRODUCT", "raven"),
                    Pair("MODEL", "Pixel 6 Pro"),
                    Pair(
                        "FINGERPRINT",
                        "google/raven/raven:12/SD1A.210817.036/7805805:user/release-keys"
                    )
                ),
                PixelFeatures.generateFeaturesWhiteList(PixelFeatures.PIXEL_2021),
                PixelFeatures.generateFeaturesBlackList(PixelFeatures.PIXEL_2021)
            )
            return listOf(
                pixelXL_10,
                pixel2_10,
                pixel2_11,
                pixel3XL_11,
                pixel3XL_12,
                pixel5_11,
                pixel5_12,
                pixel6_12
            )
        }

        val defaultInitialDeviceIndex = 5
    }
}