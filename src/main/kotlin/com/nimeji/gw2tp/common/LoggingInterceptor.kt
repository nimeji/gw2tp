package com.nimeji.gw2tp.common

import mu.KotlinLogging
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    private val logger = KotlinLogging.logger {}

    override fun intercept(chain: Interceptor.Chain): Response {
        logger.info { "requesting ${chain.request().url()}" }

        return chain.proceed(chain.request())
    }
}