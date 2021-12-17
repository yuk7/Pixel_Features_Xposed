package com.github.yuk7.xposed.pixelizerx.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.yuk7.xposed.pixelizerx.R
import com.github.yuk7.xposed.pixelizerx.databinding.ActivityMainBinding
import com.github.yuk7.xposed.pixelizerx.ui.main.apps.AppsListFragment
import com.github.yuk7.xposed.pixelizerx.ui.main.devices.DeviceListFragment
import com.github.yuk7.xposed.pixelizerx.ui.main.setting.SettingFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_fragment, AppsListFragment()).commit()

        val bnvItemObserver = Observer<MenuItem>() {
            when (it.itemId) {
                R.id.main_bnv_applications -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_main_fragment, AppsListFragment()).commit()
                }
                R.id.main_bnv_devices -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_main_fragment, DeviceListFragment()).commit()
                }
                R.id.main_bnv_others -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_main_fragment, SettingFragment()).commit()
                }
            }
        }
        viewModel.navSelectedItem.observe(this, bnvItemObserver)

    }
}