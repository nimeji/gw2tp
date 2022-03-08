package com.nimeji.gw2tp.item.application.service

import com.nimeji.gw2tp.item.adapter.out.network.ItemListingsDto
import com.nimeji.gw2tp.item.adapter.out.network.ListingDto
import com.nimeji.gw2tp.item.application.port.`in`.UpdateItemListingsUseCase
import com.nimeji.gw2tp.item.application.port.out.ItemDatabasePort
import com.nimeji.gw2tp.item.application.port.out.ItemListingsDataSourcePort
import com.nimeji.gw2tp.item.application.port.out.ItemListingsDatabasePort
import com.nimeji.gw2tp.item.domain.ItemListingsPriceAggregate
import com.nimeji.gw2tp.item.domain.ItemListingsPriceAggregateList
import com.nimeji.gw2tp.item.domain.Percentile
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.temporal.ChronoUnit
import javax.transaction.Transactional

@Service
class UpdateItemListingsService (
    @Autowired val itemListingsDataSourcePort: ItemListingsDataSourcePort,
    @Autowired val itemListingsDatabasePort: ItemListingsDatabasePort,
    @Autowired val itemDatabasePort: ItemDatabasePort,
    @Autowired val clock: Clock,
) : UpdateItemListingsUseCase {
    val logger = KotlinLogging.logger {}

    @Transactional
    override fun updateListings() {
        logger.info { "Starting to fetch listings data"}

        val validItemIds = itemDatabasePort.availableIds()
        val listingIds = itemListingsDataSourcePort.availableItemListingsIds()
        val exclude = listingIds.minus(validItemIds)

        itemListingsDataSourcePort
            .retrieveAllItemListings()
            .chunked(200)
            .forEach { listingsList ->
               itemListingsDatabasePort.insertAll(
                   listingsList
                       .filter { !exclude.contains(it.id) }
                       .map { mapFromItemListingsDto(it) }
               )
            }
        logger.info { "finished fetching listings data" }
    }

    override fun prune() {
        itemListingsDatabasePort.pruneBefore(clock.instant().minus(30, ChronoUnit.DAYS))
    }

    fun mapFromItemListingsDto(itemListingsDto: ItemListingsDto): ItemListingsWithPriceAggregate {
        val buyAggregates = createAggregations(itemListingsDto.buys.sortedByDescending { it.unit_price })
        val sellAggregates = createAggregations(itemListingsDto.sells.sortedBy { it.unit_price })

        return ItemListingsWithPriceAggregate(
            itemListingsDto.id,
            itemListingsDto.buys,
            itemListingsDto.sells,
            ItemListingsPriceAggregateList(
                Percentile.values().map {
                    ItemListingsPriceAggregate(it, buyAggregates[it], sellAggregates[it])
                }
            )
        )
    }

    fun createAggregations(listings: List<ListingDto>): Map<Percentile, Int> {
        val result = mutableMapOf<Percentile, Int>()

        Percentile.values().forEach {
            val aggregate = aggregatePrice(listings, it)
            if (aggregate != null) {
                result[it] = aggregate
            }
        }

        return result
    }

    fun aggregatePrice(listings: List<ListingDto>, percentile: Percentile): Int? {
        val totalVolume = listings.sumOf { it.quantity }
        if (totalVolume < 100) {
            return null
        }

        val targetVolume = (totalVolume * percentile.value).toInt()
        var count = targetVolume
        var sum = 0
        for (listing in listings) {
            if (count - listing.quantity < 0) {
                sum += listing.unit_price * count
                break
            }
            count -= listing.quantity
            sum += listing.quantity * listing.unit_price
        }

        return sum/targetVolume
    }
}