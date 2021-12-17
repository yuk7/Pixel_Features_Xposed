package com.github.yuk7.xposed.pixelizerx.data.db.emulatedevice

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "emulate_device", indices = [Index(value = ["device_name"], unique = true)])
class EmulateDevice: Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "device_name")
    var deviceName: String = ""

    @ColumnInfo(name = "permission_denylist")
    var permissionDenyList: List<String> = listOf()

    @ColumnInfo(name = "permission_allowlist")
    var permissionAllowList: List<String> = listOf()

    @ColumnInfo(name = "props_overrides")
    var props: Map<String, String> = mapOf()


    constructor(deviceName: String, props: Map<String, String>, permissionAllowList: List<String>, permissionDenyList: List<String>) {
        this.deviceName = deviceName
        this.permissionAllowList = permissionAllowList
        this.permissionDenyList = permissionDenyList
        this.props = props
    }
}