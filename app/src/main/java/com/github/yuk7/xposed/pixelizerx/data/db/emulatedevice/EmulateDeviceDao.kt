package com.github.yuk7.xposed.pixelizerx.data.db.emulatedevice

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EmulateDeviceDao {
    @Query("SELECT * FROM emulate_device")
    fun getAll(): List<EmulateDevice>

    @Query("SELECT * FROM emulate_device WHERE id = :id LIMIT 1")
    fun findById(id: Int): EmulateDevice?

    @Query("SELECT * FROM emulate_device WHERE device_name = :deviceName LIMIT 1")
    fun findByDeviceName(deviceName: String): EmulateDevice?

    @Insert
    fun insert(emulateDevice: EmulateDevice)

    @Insert
    fun insertAll(vararg emulateDevice: EmulateDevice)

    @Insert
    fun insertAll(emulateDeviceList: List<EmulateDevice>)

    @Delete
    fun delete(emulateDevice: EmulateDevice)

    @Query("DELETE FROM emulate_device")
    fun deleteAll()
}