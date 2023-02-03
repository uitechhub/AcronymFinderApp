package com.albertson.codetest.acronymfinder.di.components

import com.albertson.codetest.acronymfinder.di.modules.AcronymFinderModule
import com.albertson.codetest.acronymfinder.di.modules.ApplicationModule
import com.albertson.codetest.acronymfinder.di.modules.NetworkModule
import com.albertson.codetest.acronymfinder.di.modules.ViewModelsModule
import com.albertson.codetest.acronymfinder.di.qualifiers.ApplicationScope
import com.albertson.codetest.acronymfinder.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@ApplicationScope
@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AcronymFinderModule::class,
        NetworkModule::class,
        ViewModelsModule::class
    ]
)
interface ApplicationComponent {
    //inject sub-components
    fun activityComponent(): ActivityComponent.Factory
    fun fragmentComponent(): FragmentComponent.Factory

    fun inject(viewModel: MainViewModel)

}