package com.santimattius.basic.skeleton.di

import com.santimattius.basic.skeleton.core.SayHelloServices
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class AppModule {

    @Factory
    @Single
    fun provideSayHelloServices(): SayHelloServices {
        return SayHelloServices()
    }
}