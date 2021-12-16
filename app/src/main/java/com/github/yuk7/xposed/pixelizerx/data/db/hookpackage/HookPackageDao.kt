package com.github.yuk7.xposed.pixelizerx.data.db.hookpackage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.github.yuk7.xposed.pixelizerx.data.db.emulatedevice.EmulateDevice

@Dao
interface HookPackageDao {
    @Query("SELECT * FROM hook_package")
    fun getAll(): List<HookPackage>

    @Query("SELECT * FROM hook_package WHERE id = :id LIMIT 1")
    fun findById(id: Int): HookPackage?

    @Query("SELECT * FROM hook_package WHERE package_name = :packageName LIMIT 1")
    fun findByPackageName(packageName: String): HookPackage?

    @Insert
    fun insert(hookPackage: HookPackage)

    @Insert
    fun insertAll(vararg hookPackage: HookPackage)

    @Insert
    fun insertAll(hookPackageList: List<HookPackage>)

    @Delete
    fun delete(hookPackage: HookPackage)

    @Query("DELETE FROM hook_package")
    fun deleteAll()
}