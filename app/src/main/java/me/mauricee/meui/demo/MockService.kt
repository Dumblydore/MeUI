package me.mauricee.meui.demo

import io.reactivex.Flowable
import io.reactivex.Single
import kotlin.random.Random

class MockService {
    val randomNumber: Single<Int> = Single.fromCallable { Random.nextInt() }

    fun rangeOf(count: Int = 5): Single<List<Int>> = Flowable.range(0, count)
        .concatMapSingle { randomNumber }.toList()
}