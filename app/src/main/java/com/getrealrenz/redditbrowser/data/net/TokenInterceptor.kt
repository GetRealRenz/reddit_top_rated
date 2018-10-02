package com.getrealrenz.redditbrowser.data.net

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created on 02.10.2018.
 */
class TokenInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        var original = chain?.request()

        return chain!!.proceed(original)
    }
}