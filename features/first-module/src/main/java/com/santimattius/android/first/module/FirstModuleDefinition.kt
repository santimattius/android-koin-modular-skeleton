package com.santimattius.android.first.module

import org.koin.core.annotation.Module
import org.koin.core.annotation.Single


@Module
class FirstModule{

    @Single
    fun provideFirstModuleServices(): FirstModuleServices {
        return FirstModuleServices()
    }
}