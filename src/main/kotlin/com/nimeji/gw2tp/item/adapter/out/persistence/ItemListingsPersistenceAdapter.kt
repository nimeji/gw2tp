package com.nimeji.gw2tp.item.adapter.out.persistence

import com.nimeji.gw2tp.item.application.port.out.ItemListingsDatabasePort
import com.nimeji.gw2tp.item.application.service.ItemListingsWithPriceAggregate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.Instant

@Component
class ItemListingsPersistenceAdapter (
    @Autowired val itemListingsRepository: ItemListingsRepository,
    @Autowired val clock: Clock,
) : ItemListingsDatabasePort {
    override fun insertAll(itemListings: List<ItemListingsWithPriceAggregate>) {
        itemListingsRepository.saveAll(itemListings.map { ItemListingsEntity(it, clock.instant()) })
    }

    override fun pruneBefore(timestamp: Instant) {
        itemListingsRepository.deleteByTimestampIsLessThan(timestamp)
    }
}