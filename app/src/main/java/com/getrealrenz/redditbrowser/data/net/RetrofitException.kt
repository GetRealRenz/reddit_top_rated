package com.getrealrenz.redditbrowser.data.net

import java.io.IOException

import retrofit2.HttpException
import retrofit2.Response

class RetrofitException  constructor(
        message: String?,
        val url: String?,
        val response: Response<*>?,
        val kind: Kind?, exception: Throwable?) : RuntimeException(message, exception) {

    enum class Kind {
        NETWORK,
        HTTP,
        UNEXPECTED
    }

    companion object {

        private fun httpError(url: String, response: Response<*>): RetrofitException {
            val message = response.code().toString() + " " + response.message()
            return RetrofitException(message, url, response, Kind.HTTP, null)
        }

        private fun networkError(exception: IOException): RetrofitException {
            return RetrofitException(exception.message, null, null, Kind.NETWORK, exception)
        }

        private fun unexpectedError(exception: Throwable): RetrofitException {
            return RetrofitException(exception.message, null, null, Kind.UNEXPECTED, exception)
        }

        fun asRetrofitException(throwable: Throwable): RetrofitException {
            if (throwable is HttpException) {
                val response = throwable.response()
                return RetrofitException.httpError(
                        response.raw().request().url().toString(),
                        response)
            }
            return if (throwable is IOException) {
                RetrofitException.networkError(throwable)
            } else RetrofitException.unexpectedError(throwable)

        }
    }
}

