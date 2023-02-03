package com.albertson.codetest.acronymfinder.di.components

import com.albertson.codetest.acronymfinder.di.modules.ViewModelsModule
import com.albertson.codetest.acronymfinder.ui.main.MainFragment
import com.albertson.codetest.acronymfinder.di.qualifiers.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(
    modules = [
        ViewModelsModule::class
    ]
)
interface FragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentComponent
    }

    fun inject(fragment: MainFragment)

}