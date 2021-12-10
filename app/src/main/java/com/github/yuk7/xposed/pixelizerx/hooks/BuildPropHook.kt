package com.github.yuk7.xposed.pixelizerx.hooks

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook.MethodHookParam

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge

import de.robv.android.xposed.XposedHelpers

import de.robv.android.xposed.callbacks.XC_LoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam


class BuildPropHook: IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: LoadPackageParam?) {
        if (lpparam == null) {
            return
        }
        XposedBridge.log("loaded pkg=" + lpparam.packageName)
        var hookClass = XposedHelpers.findClass("android.os.Build", lpparam?.classLoader)
        XposedHelpers.setStaticObjectField(hookClass, "BOARD" , "HOOKED_BOARD")
        XposedHelpers.setStaticObjectField(hookClass, "BOOTLOADER", "HOOKED_BL")
        XposedHelpers.setStaticObjectField(hookClass, "BRAND", "HOOKED_BRAND")
        XposedHelpers.setStaticObjectField(hookClass, "DEVICE", "HOOKED_DEVICE")
        XposedHelpers.setStaticObjectField(hookClass, "FINGERPRINT", "HOOKED_FINGERPRINT")
        XposedHelpers.setStaticObjectField(hookClass, "HARDWARE", "HOOKED_HARDWARE")
        XposedHelpers.setStaticObjectField(hookClass, "MANUFACTURER", "HOOKED_MANUFACTURER")
        XposedHelpers.setStaticObjectField(hookClass, "MODEL", "HOOKED_MODEL")


    }

}