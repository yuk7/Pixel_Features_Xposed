package com.github.yuk7.xposed.pixelizerx.hooks

import android.app.Application
import android.content.Context
import android.net.Uri
import com.github.yuk7.xposed.pixelizerx.BuildConfig
import com.github.yuk7.xposed.pixelizerx.providers.SettingProvider
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class PermissionHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        if (lpparam == null) {
            return
        }
        if (lpparam.packageName == BuildConfig.APPLICATION_ID) {
            return
        }


        XposedHelpers.findAndHookMethod(
            Application::class.java,
            "attach",
            Context::class.java,
            object :
                XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam) {
                    super.afterHookedMethod(param)
                    val context = param.args[0] as Context

                    XposedBridge.log("perm hook start: ${context.packageName}")

                    val cursorAllow = context.contentResolver.query(
                        Uri.parse(SettingProvider.providerUri + "/" + SettingProvider.keyPermissionAllow),
                        null, null, null, null
                    ) ?: return
                    val cursorDeny = context.contentResolver.query(
                        Uri.parse(SettingProvider.providerUri + "/" + SettingProvider.keyPermissionDeny),
                        null, null, null, null
                    ) ?: return
                    cursorAllow.moveToFirst()
                    val allowList: MutableList<String> = mutableListOf()
                    if (cursorAllow.count > 0) {
                        do {
                            allowList.add(cursorAllow.getString(0))
                        } while (cursorAllow.moveToNext())
                    }

                    cursorDeny.moveToFirst()
                    val denyList: MutableList<String> = mutableListOf()
                    if (cursorDeny.count > 0) {
                        do {
                            denyList.add(cursorDeny.getString(0))
                        } while (cursorDeny.moveToNext())
                    }

                    cursorAllow.close()
                    cursorDeny.close()

                    hookMain(lpparam, allowList, denyList)
                    XposedBridge.log("feature hook initialized")
                }
            })
    }

    private val applicationManager = "android.app.ApplicationPackageManager"
    private fun hookMain(
        lpparam: XC_LoadPackage.LoadPackageParam,
        allowList: List<String>,
        denyList: List<String>
    ) {
        XposedBridge.log("permission hooking:" + lpparam.packageName)
        XposedHelpers.findAndHookMethod(
            applicationManager,
            lpparam.classLoader,
            "hasSystemFeature",
            String::class.java,
            Int::class.java,
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    super.beforeHookedMethod(param)
                    hookedHasSystemFeature(param, allowList, denyList)
                }
            })
        XposedHelpers.findAndHookMethod(
            applicationManager,
            lpparam.classLoader,
            "hasSystemFeature",
            String::class.java,
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    super.beforeHookedMethod(param)
                    hookedHasSystemFeature(param, allowList, denyList)
                }
            })
    }

    private fun hookedHasSystemFeature(
        param: XC_MethodHook.MethodHookParam?,
        allowList: List<String>,
        denyList: List<String>
    ) {
        if (param == null) {
            return
        }
        param.args.forEach {
            if (it is String) {
                if (it in allowList) {
                    XposedBridge.log("hasSystemFeature called and returned true in $it")
                    param.result = true
                } else if (it in denyList) {
                    XposedBridge.log("hasSystemFeature called and returned false in $it")
                    param.result = false
                }
            }
        }

    }
}