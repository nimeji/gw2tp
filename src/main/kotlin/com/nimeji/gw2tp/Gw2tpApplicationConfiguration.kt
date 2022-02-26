package com.nimeji.gw2tp

import com.fasterxml.jackson.databind.DeserializationFeature
import com.nimeji.gw2tp.common.LoggingInterceptor
import com.nimeji.gw2tp.common.RateLimitInterceptor
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import java.time.Clock
import java.time.Duration

@Configuration
@EnableAsync
class Gw2tpApplicationConfiguration {
    @Bean("gw2api")
    fun getGW2ApiHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(HttpTimeout) {
                socketTimeoutMillis = 1000 * 60 * 5
            }
            install(JsonFeature) {
                serializer = JacksonSerializer {
                    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                }
            }
            engine {
                addInterceptor(RateLimitInterceptor(1, Duration.ofSeconds(1), Duration.ofMinutes(10)))
                addInterceptor(LoggingInterceptor())
            }
        }
    }

    @Bean
    fun clock(): Clock {
        return Clock.systemDefaultZone()
    }
}