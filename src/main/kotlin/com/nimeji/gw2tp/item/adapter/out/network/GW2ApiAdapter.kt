package com.nimeji.gw2tp.item.adapter.out.network

import com.nimeji.gw2tp.item.application.port.out.ItemDataSourcePort
import com.nimeji.gw2tp.item.application.port.out.ItemListingsDataSourcePort
import com.nimeji.gw2tp.item.domain.Item
import com.nimeji.gw2tp.item.domain.ItemListings
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.aspectj.util.FileUtil.flatten
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import java.time.Clock

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

    override fun retrieveItemListingsForItemIds(ids: Set<Int>): Iterable<ItemListingsDto> {
        val result = ids.chunked(200)
            .asSequence()
            .map { retrieveItemListings(it) }
            .flatten()
        return result.asIterable()
    }

    override fun availableItemListingsIds(): Set<Int> {
        return runBlocking {
            httpClient.get(URLBuilder(gw2apiBaseUrl).path("v2", "commerce", "listings").build())
        }
    }

    fun retrieveItemListings(ids: List<Int>): List<ItemListingsDto> {
        return runBlocking {
            httpClient.get(URLBuilder(gw2apiBaseUrl).path("v2", "commerce", "listings").build()) {
                parameter("ids", ids.joinToString(","))
                parameter("lang", "en")
            }
        }
    }

}