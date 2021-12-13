package com.github.yuk7.xposed.pixelizerx.data.db.hookpackage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HookPackageDao {
    @Query("SELECT * FROM hook_package")
    fun getAll(): List<HookPackage>

    @Query("SELECT * FROM hook_package WHERE id = :id LIMIT 1")
    fun findById(id: Int): HookPackage

    @Insert
    fun insert(hookPackage: HookPackage)

    @Delete
    fun delete(hookPackage: HookPackage)

    @Query("DELETE FROM hook_package")
    fun deleteAll()
}