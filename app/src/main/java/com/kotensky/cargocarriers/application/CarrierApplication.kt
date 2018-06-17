package com.kotensky.cargocarriers.application

import android.app.Application
import com.workhardapp.di.components.ApplicationComponent
import com.workhardapp.di.components.DaggerApplicationComponent
import com.workhardapp.di.modules.ApplicationModule

class CarrierApplication : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

}