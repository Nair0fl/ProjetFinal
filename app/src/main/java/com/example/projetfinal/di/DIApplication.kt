package com.example.projetfinal.di

import android.app.Application
import com.example.projetfinal.di.AppComponent
import com.example.projetfinal.di.DaggerAppComponent

class DIApplication : Application() {
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().application(this).build()!!
    }

    companion object {
        //Attributes
        private lateinit var instance: DIApplication
        fun getAppComponent(): AppComponent? {
            return instance.appComponent
        }
    }
}