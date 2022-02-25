package com.nimeji.gw2tp.item.adapter.out.persistence

import com.nimeji.gw2tp.item.adapter.out.network.ItemListingsDto
import com.nimeji.gw2tp.item.application.port.out.ItemListingsDatabasePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.Instant

@Component
class ItemListingsPersistenceAdapter (
    @Autowired val itemListingsRepository: ItemListingsRepository,
    @Autowired val clock: Clock,
) : ItemListingsDatabasePort {

    override fun insert(itemListingsDto: ItemListingsDto) {
        itemListingsRepository.save(ItemListingsEntity(itemListingsDto, clock.instant()))
    }

    override fun pruneBefore(timestamp: Instant) {
        itemListingsRepository.deleteByTimestampIsLessThan(timestamp)
    }
}