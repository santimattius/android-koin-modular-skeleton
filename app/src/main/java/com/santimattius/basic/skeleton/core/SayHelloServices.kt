package com.santimattius.basic.skeleton.core

import kotlinx.coroutines.delay
import kotlin.random.Random

class SayHelloServices {

    suspend fun sayHello(): String {
        delay(1000)
        return if (Random.nextBoolean()) "Hello, Android!" else "Goodbye, Android!"
    }

}