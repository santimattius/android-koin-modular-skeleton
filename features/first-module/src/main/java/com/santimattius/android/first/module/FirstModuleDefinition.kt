package com.santimattius.android.first.module

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val firstModule = module {
    singleOf(::FirstModuleServices)
}