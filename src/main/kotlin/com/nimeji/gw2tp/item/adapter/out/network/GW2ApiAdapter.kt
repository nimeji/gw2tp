package com.nimeji.gw2tp.item.adapter.out.network

import com.nimeji.gw2tp.item.application.port.out.ItemDataSourcePort
import com.nimeji.gw2tp.item.domain.Item
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Repository
class GW2ApiAdapter(
    @Qualifier("gw2api") private val httpClient: HttpClient,
    gw2apiBaseUrl: String = "https://api.guildwars2.com/"
) : ItemDataSourcePort {
    val gw2apiBaseUrl: Url = Url(gw2apiBaseUrl)

    override fun retrieveAllItems(): Iterable<Item> {
        val ids = retrieveAllItemIds()
        val result = ids.chunked(200).asSequence().map { retrieveIds(it) }.flatten().map { it.toDomain() }
        return result.asIterable()
    }

    override fun retrieveItemsById(ids: List<Item.ItemId>): Iterable<Item> {
        TODO("Not yet implemented")
    }

    fun retrieveAllItemIds(): List<Int> {
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
}