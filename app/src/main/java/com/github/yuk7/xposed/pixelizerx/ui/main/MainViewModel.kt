package com.github.yuk7.xposed.pixelizerx.ui.main

import android.view.MenuItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var navSelectedItem = MutableLiveData<MenuItem>()

    fun onNavigationItemSelected(item: MenuItem): Boolean {
        navSelectedItem.value = item
        return true
    }
}