package com.albertson.codetest.acronymfinder

import android.app.Application
import com.albertson.codetest.acronymfinder.di.components.ApplicationComponent
import com.albertson.codetest.acronymfinder.di.components.DaggerApplicationComponent
import com.albertson.codetest.acronymfinder.di.modules.AcronymFinderModule
import com.albertson.codetest.acronymfinder.di.modules.ApplicationModule
import com.albertson.codetest.acronymfinder.di.modules.NetworkModule

class AcronymFinderApp: Application() {

    private lateinit var applicationComponent: ApplicationComponent

    private fun initialize() {
        injectAppComponent()
        //add other app initialization code here.
    }

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun injectAppComponent() {
        if (!::applicationComponent.isInitialized) {
            applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .networkModule(NetworkModule())
                .acronymFinderModule(AcronymFinderModule())
                .build()
        }
    }

    fun getApplicationComponent() = applicationComponent
}