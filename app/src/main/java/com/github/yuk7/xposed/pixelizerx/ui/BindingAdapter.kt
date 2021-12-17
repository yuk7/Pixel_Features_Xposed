package com.github.yuk7.xposed.pixelizerx.ui

import androidx.databinding.BindingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("onNavigationItemSelected")
        fun setOnNavigationItemSelected(view: BottomNavigationView, listener: NavigationBarView.OnItemSelectedListener?){
            view.setOnItemSelectedListener(listener)
        }
    }
}