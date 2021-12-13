package com.github.yuk7.xposed.pixelizerx.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.yuk7.xposed.pixelizerx.data.db.emulatedevice.EmulateDevice
import com.github.yuk7.xposed.pixelizerx.data.db.emulatedevice.EmulateDeviceDao
import com.github.yuk7.xposed.pixelizerx.data.db.hookpackage.HookPackage
import com.github.yuk7.xposed.pixelizerx.data.db.hookpackage.HookPackageDao

@Database(entities = [EmulateDevice::class, HookPackage::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun EmulateDeviceDao(): EmulateDeviceDao
    abstract fun HookPackageDao(): HookPackageDao

    companion object {
        private var instance: AppDB? = null

        fun getInstance(context: Context): AppDB {
            if (instance == null) {
                synchronized(AppDB::class) {
                    instance =
                        Room.databaseBuilder(context.applicationContext, AppDB::class.java, "data")
                            .build()
                }
            }
            return instance!!
        }

        fun destoroyInstance() {
            instance = null
        }
    }
}