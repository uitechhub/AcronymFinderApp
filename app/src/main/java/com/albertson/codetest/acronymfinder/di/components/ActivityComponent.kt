package com.albertson.codetest.acronymfinder.di.components

import com.albertson.codetest.acronymfinder.ui.main.MainActivity
import com.albertson.codetest.acronymfinder.di.modules.ViewModelsModule
import com.albertson.codetest.acronymfinder.di.qualifiers.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [
    ViewModelsModule::class
])
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    // inject activities here
    fun inject(activity: MainActivity)

}