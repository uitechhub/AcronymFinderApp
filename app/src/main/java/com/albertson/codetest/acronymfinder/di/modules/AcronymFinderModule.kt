package com.albertson.codetest.acronymfinder.di.modules

import com.albertson.codetest.acronymfinder.network.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AcronymFinderModule {

    @Provides
    @Singleton
    internal fun provideAcromineDataSource(acromineApi: AcromineApi): AcromineDataSource {
        return AcromineDataSourceImpl(acromineApi)
    }

    @Provides
    @Singleton
    internal fun provideAcromineRepository(acromineDataSource: AcromineDataSource): AcromineRepository {
        return AcromineRepositoryImpl(acromineDataSource)
    }
}