package com.example.movilbox.extension

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.movilbox.di.GeneralError
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

fun Throwable.toError(): GeneralError = when (this) {
    is IOException -> GeneralError.Connectivity
    is HttpException -> GeneralError.Server
    else -> GeneralError.Unknown
}

suspend fun <T> tryCall(action: suspend () -> T): Either<GeneralError, T> = try {
    action().right()
} catch (e: Exception) {
    Timber.e(e)
    e.toError().left()
}