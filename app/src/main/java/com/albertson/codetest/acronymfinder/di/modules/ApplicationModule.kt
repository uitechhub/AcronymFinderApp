package com.albertson.codetest.acronymfinder.di.modules

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.albertson.codetest.acronymfinder.di.qualifiers.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
open class ApplicationModule(private val app: Application) {

    @Provides
    @ApplicationScope
    open fun provideResources(): Resources = app.resources
}