package com.getrealrenz.redditbrowser.data.net

import java.io.IOException

import retrofit2.HttpException
import retrofit2.Response

class RetrofitException  constructor(
        message: String?,
        /**
         * The request URL which produced the error.
         */
        val url: String?,
        /**
         * SettingsResponse object containing status code, headers, body, etc.
         */
        val response: Response<*>?,
        /**
         * The event kind which triggered this error.
         */
        val kind: Kind?, exception: Throwable?) : RuntimeException(message, exception) {

    /**
     * Identifies the event kind which triggered a [RetrofitException].
     */
    enum class Kind {
        /**
         * An [IOException] occurred while communicating to the server.
         */
        NETWORK,
        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
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
            // We had non-200 http error
            if (throwable is HttpException) {
                val response = throwable.response()
                return RetrofitException.httpError(
                        response.raw().request().url().toString(),
                        response)
            }
            // A network error happened
            return if (throwable is IOException) {
                RetrofitException.networkError(throwable)
            } else RetrofitException.unexpectedError(throwable)

            // We don't know what happened. We need to simply convert to an unknown error

        }
    }
}

