package com.github.yuk7.xposed.pixelizerx.data.providers

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.util.Log
import com.github.yuk7.xposed.pixelizerx.BuildConfig
import com.github.yuk7.xposed.pixelizerx.data.db.AppDB
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class SettingProvider: ContentProvider() {
    companion object {
        const val keyProps = "props"
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
        val name = File(uri.path).name

        val cursor = MatrixCursor(arrayOf("name", "value"))
        val db = AppDB.getInstance(context!!)
        val pkg = db.HookPackageDao().findByPackageName(name)
        val device = db.EmulateDeviceDao().findById(pkg.emulateDeviceId)

        cursor.addRow(arrayOf(keyProps, Json.encodeToString(device.props)))
        cursor.addRow(arrayOf(keyPermissionAllow, Json.encodeToString(device.permissionAllowList)))
        cursor.addRow(arrayOf(keyPermissionDeny, Json.encodeToString(device.permissionDenyList)))

        return cursor
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