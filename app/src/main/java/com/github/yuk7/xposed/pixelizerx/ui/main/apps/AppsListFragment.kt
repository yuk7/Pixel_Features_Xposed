package com.github.yuk7.xposed.pixelizerx.ui.main.apps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.yuk7.xposed.pixelizerx.R
import android.content.pm.ApplicationInfo

import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.yuk7.xposed.pixelizerx.data.db.AppDB
import com.github.yuk7.xposed.pixelizerx.data.initdata.devices.PixelPropsDef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AppsListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_appslist, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.Default) {
                val list: MutableList<String> = mutableListOf()
                val pm = requireContext().packageManager
                val appInfoList = pm.getInstalledApplications(PackageManager.GET_META_DATA)
                for (appInfo in appInfoList) {
                    list.add(pm.getApplicationLabel(appInfo).toString() + ":" + appInfo.packageName)
                }
                list
            }.let {
                view.findViewById<RecyclerView>(R.id.fragment_applist_recycler).apply {
                    setHasFixedSize(true)
                    layoutManager = GridLayoutManager(context, 1)
                    adapter = AppListRecyclerAdapter(context, it)
                }
            }
        }

    }
}