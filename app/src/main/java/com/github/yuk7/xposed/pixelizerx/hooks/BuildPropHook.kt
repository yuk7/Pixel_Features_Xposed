package com.github.yuk7.xposed.pixelizerx.hooks

import android.app.Application
import android.content.Context
import android.net.Uri
import com.github.yuk7.xposed.pixelizerx.BuildConfig
import com.github.yuk7.xposed.pixelizerx.data.providers.SettingProvider
import de.robv.android.xposed.*
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
import de.robv.android.xposed.XposedHelpers


class BuildPropHook: IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: LoadPackageParam?) {
        if (lpparam == null) {
            return
        }
        if (lpparam.packageName == BuildConfig.APPLICATION_ID) {
            return
        }
        XposedHelpers.findAndHookMethod(Application::class.java, "attach", Context::class.java, object:XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                super.afterHookedMethod(param)
                val context = param.args[0] as Context

                val hookClass = XposedHelpers.findClass("android.os.Build", lpparam.classLoader)
                val resolver = context.contentResolver
                val cursor = resolver.query(Uri.parse(SettingProvider.providerUri+"/"+SettingProvider.keyProps),
                    null, null, null, null)?: return
                XposedBridge.log("props hooking: ${context.packageName}")
                cursor!!.moveToFirst()
                do {
                    XposedBridge.log("set build field: ${cursor.getString(0)}=${cursor.getString(1)}")
                    XposedHelpers.setStaticObjectField(hookClass, cursor.getString(0), cursor.getString(1))
                }while (cursor.moveToNext())
            }
        })

    }
}