package com.nimeji.gw2tp.item.application.service

import com.nimeji.gw2tp.item.application.port.`in`.UpdateItemListingsUseCase
import com.nimeji.gw2tp.item.application.port.out.ItemDatabasePort
import com.nimeji.gw2tp.item.application.port.out.ItemListingsDataSourcePort
import com.nimeji.gw2tp.item.application.port.out.ItemListingsDatabasePort
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

        val itemListings = itemListingsDataSourcePort.retrieveItemListingsForItemIds(validItemIds.intersect(listingIds))
        itemListings.forEach {
            itemListingsDatabasePort.insert(it)
        }
        logger.info { "finished fetching listings data" }
    }

    override fun prune() {
        itemListingsDatabasePort.pruneBefore(clock.instant().minus(30, ChronoUnit.DAYS))
    }
}