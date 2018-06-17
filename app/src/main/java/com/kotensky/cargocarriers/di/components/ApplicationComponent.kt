package com.workhardapp.di.components

import android.content.Context
import com.kotensky.cargocarriers.view.activities.MainActivity
import com.workhardapp.di.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

    fun context(): Context

    fun inject(mainActivity: MainActivity)

}