package com.nimeji.gw2tp.item.adapter.out.network

import com.nimeji.gw2tp.item.application.port.out.ItemDataSourcePort
import com.nimeji.gw2tp.item.application.port.out.ItemListingsDataSourcePort
import com.nimeji.gw2tp.item.domain.Item
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Repository
class GW2ApiAdapter(
    @Qualifier("gw2api") private val httpClient: HttpClient,
) : ItemDataSourcePort, ItemListingsDataSourcePort {
    val gw2apiBaseUrl: Url = Url("https://api.guildwars2.com/")

    override fun retrieveBuildVersion(): Int {
        return runBlocking<BuildVersionDto> {
            httpClient.get(URLBuilder(gw2apiBaseUrl).path("v2", "build").build())
        }.id
    }

    override fun retrieveItemsById(ids: Collection<Int>): Iterable<Item> {
        val buildVersion = retrieveBuildVersion()
        val result = ids.chunked(200)
            .asSequence()
            .map { retrieveIds(it) }
            .flatten()
            .map { it.toDomain(buildVersion) }
        return result.asIterable()
    }

    override fun availableItemIds(): Set<Int> {
        return runBlocking {
            httpClient.get(URLBuilder(gw2apiBaseUrl).path("v2", "items").build())
        }
    }

    fun retrieveIds(ids: List<Int>): List<ItemDto> {
        return runBlocking {
            httpClient.get(URLBuilder(gw2apiBaseUrl).path("v2", "items").build()) {
                parameter("ids", ids.joinToString(","))
                parameter("lang", "en")
            }
        }
    }

    override fun retrieveAllItemListings() = sequence<ItemListingsDto> {
        var page = 0
        do {
            val httpResponse: HttpResponse = runBlocking {
                httpClient.get(URLBuilder(gw2apiBaseUrl).path("v2", "commerce", "listings").build()) {
                    parameter("page", page)
                    parameter("page_size", 200)
                }
            }

            val pageTotal: Int = httpResponse.headers["x-page-total"]?.toInt()
                ?: throw IllegalArgumentException("x-page-total header is unset or invalid")
            page += 1

            val listings: List<ItemListingsDto> = runBlocking { httpResponse.receive() }
            listings.forEach { yield(it) }
        } while(page < pageTotal)
    }

    override fun availableItemListingsIds(): Set<Int> {
        return runBlocking {
            httpClient.get(URLBuilder(gw2apiBaseUrl).path("v2", "commerce", "listings").build())
        }
    }
}