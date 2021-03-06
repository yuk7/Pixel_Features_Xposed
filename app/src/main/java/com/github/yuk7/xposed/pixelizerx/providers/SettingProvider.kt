package com.github.yuk7.xposed.pixelizerx.providers

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.util.Log
import com.github.yuk7.xposed.pixelizerx.BuildConfig
import com.github.yuk7.xposed.pixelizerx.data.db.AppDB
import java.io.File

class SettingProvider : ContentProvider() {
    companion object {
        const val keyBuild = "build"
        const val keyPermissionAllow = "permission_allowlist"
        const val keyPermissionDeny = "permission_denylist"
        const val providerUri = "content://" + BuildConfig.APPLICATION_ID + ".SettingProvider"
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selectionClause: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val name = File(uri.path!!).name
        if (context == null || callingPackage == null) {
            return null
        }

        Log.d("SettingProvider", "${callingPackage}: ${name}")

        val db = AppDB.getInstance(context!!)
        val pkg = db.HookPackageDao().findByPackageName(callingPackage.toString()) ?: return null
        val device = db.EmulateDeviceDao().findById(pkg.emulateDeviceId)

        return when (name) {
            keyBuild -> {
                val cursor = MatrixCursor(arrayOf("key", "value"))
                if (pkg.enabled) {
                    device!!.build.forEach {
                        cursor.addRow(arrayOf(it.key, it.value))
                    }
                }
                cursor
            }
            keyPermissionAllow -> {
                val cursor = MatrixCursor(arrayOf("permission"))
                if (pkg.enabled) {
                    device!!.permissionAllowList.forEach {
                        cursor.addRow(arrayOf(it))
                    }
                }
                cursor
            }
            keyPermissionDeny -> {
                val cursor = MatrixCursor(arrayOf("permission"))
                if (pkg.enabled) {
                    device!!.permissionDenyList.forEach {
                        cursor.addRow(arrayOf(it))
                    }
                }
                cursor
            }
            else -> {
                Log.d(
                    "SettingProvider",
                    "Query Argument Invalid [$name] from: $callingPackage"
                )
                null
            }
        }
    }

    override fun getType(p0: Uri): String? {
        return ""
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        return null
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        return 0
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        return 0
    }

}