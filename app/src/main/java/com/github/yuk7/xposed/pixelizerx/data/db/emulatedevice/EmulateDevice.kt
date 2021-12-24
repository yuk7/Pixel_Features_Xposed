package com.github.yuk7.xposed.pixelizerx.data.db.emulatedevice

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "emulate_device", indices = [Index(value = ["device_name", "variant_tag"], unique = true)])
class EmulateDevice: Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "device_name")
    var deviceName: String = ""

    @ColumnInfo(name = "variant_tag")
    var variant: String = ""

    @ColumnInfo(name = "hook_enabled")
    var enabled: Boolean = true

    @ColumnInfo(name = "permission_denylist")
    var permissionDenyList: List<String> = listOf()

    @ColumnInfo(name = "permission_allowlist")
    var permissionAllowList: List<String> = listOf()

    @ColumnInfo(name = "build_overrides")
    var build: Map<String, String> = mapOf()


    constructor(deviceName: String, variant: String, build: Map<String, String>, permissionAllowList: List<String>, permissionDenyList: List<String>) {
        this.deviceName = deviceName
        this.variant = variant
        this.permissionAllowList = permissionAllowList
        this.permissionDenyList = permissionDenyList
        this.build = build
    }
}