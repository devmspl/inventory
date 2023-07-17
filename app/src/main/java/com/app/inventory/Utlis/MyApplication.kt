package com.app.inventory.Utlis

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.app.inventory.Api.WebServices

class MyApplication: MultiDexApplication()  {
    override fun onCreate() {
        super.onCreate()
        WebServices().create()
        MultiDex.install(applicationContext)
//        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}