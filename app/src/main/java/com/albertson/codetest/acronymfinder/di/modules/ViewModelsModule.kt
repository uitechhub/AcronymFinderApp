package com.albertson.codetest.acronymfinder.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.albertson.codetest.acronymfinder.di.qualifiers.ViewModelKey
import com.albertson.codetest.acronymfinder.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

}