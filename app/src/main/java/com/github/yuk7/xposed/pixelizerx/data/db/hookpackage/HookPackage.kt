package com.github.yuk7.xposed.pixelizerx.data.db.hookpackage

import androidx.room.*
import com.github.yuk7.xposed.pixelizerx.data.db.emulatedevice.EmulateDevice
import java.io.Serializable

@Entity(tableName = "hook_package",
    indices = [Index(value = ["package_name"], unique = true)],
    foreignKeys = [ForeignKey(entity = EmulateDevice::class,
        parentColumns = ["id"],
        childColumns = ["emulate_device_id"],
        onDelete = ForeignKey.CASCADE)])
class HookPackage: Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "package_name")
    var packageName: String = ""

    @ColumnInfo(name = "emulate_device_id", index = true)
    var emulateDeviceId: Int = 0

    constructor()

    constructor(packageName: String, emulateDeviceId: Int) {
        this.packageName = packageName
        this.emulateDeviceId = emulateDeviceId
    }
}