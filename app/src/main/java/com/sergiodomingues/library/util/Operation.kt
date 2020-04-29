package com.sergiodomingues.library.util

import io.reactivex.Completable
import io.reactivex.Single

sealed class Operation<out T> {
    data class Success<T>(val result: T) : Operation<T>()
    data class Error<T>(val throwable: Throwable) : Operation<T>()
}

val <T> Operation<T>.isSuccessful get() = this is Operation.Success<T>
val <T> Operation<T>.isError get() = this is Operation.Error<T>

fun Completable.toOperation() =
    toSingleDefault(Operation.Success(Unit) as Operation<Unit>)
        .onErrorReturn { Operation.Error(it) }

fun <T> Single<T>.toOperation(): Single<Operation<T>> =
    map { Operation.Success(it) as Operation<T> }
        .onErrorReturn { Operation.Error(it) }