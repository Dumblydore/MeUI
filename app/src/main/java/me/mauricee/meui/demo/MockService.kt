package me.mauricee.meui.demo

import io.reactivex.Single
import kotlin.random.Random

class MockService {
    val randomNumber: Single<Int> = Single.fromCallable { Random.nextInt() }
}