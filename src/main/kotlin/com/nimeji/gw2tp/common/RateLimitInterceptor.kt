package com.nimeji.gw2tp.common

import io.github.resilience4j.ratelimiter.RateLimiter
import io.github.resilience4j.ratelimiter.RateLimiterConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.time.Duration
import java.util.concurrent.TimeoutException

class RateLimitInterceptor(request: Int, period: Duration, timeout: Duration) : Interceptor {
    private val rateLimiter = createRateLimiter(request, period, timeout)

    private fun createRateLimiter(request: Int, period: Duration, timeout: Duration): RateLimiter {
        val rateLimiterConfig = RateLimiterConfig.custom()
            .limitForPeriod(request)
            .limitRefreshPeriod(period)
            .timeoutDuration(timeout)
            .build()

        return RateLimiter.of("", rateLimiterConfig)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val success = rateLimiter.acquirePermission()
        if (success) {
            return chain.proceed(chain.request())
        }
        throw TimeoutException("request could not acquire a permission")
    }
}