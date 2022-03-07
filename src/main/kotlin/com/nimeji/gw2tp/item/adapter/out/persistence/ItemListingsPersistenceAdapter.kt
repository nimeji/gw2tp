package com.nimeji.gw2tp.item.adapter.out.persistence

import com.nimeji.gw2tp.item.adapter.out.network.ItemListingsDto
import com.nimeji.gw2tp.item.application.port.out.ItemListingsDatabasePort
import com.nimeji.gw2tp.item.domain.Percentile
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.Instant

@Component
class ItemListingsPersistenceAdapter (
    @Autowired val itemListingsRepository: ItemListingsRepository,
    @Autowired val clock: Clock,
) : ItemListingsDatabasePort {

    override fun insert(
        itemListingsDto: ItemListingsDto,
        buyAggregates: Map<Percentile, Int>,
        sellAggregates: Map<Percentile, Int>
    ) {
        val aggregates = mutableListOf<ItemListingsPriceAggregationEntity>()
        Percentile.values().forEach {
            aggregates.add(ItemListingsPriceAggregationEntity(
                it,
                buyAggregates[it],
                sellAggregates[it]
            ))
            buyAggregates[it]
        }

        itemListingsRepository.save(ItemListingsEntity(itemListingsDto, clock.instant(), aggregates))
    }

    override fun pruneBefore(timestamp: Instant) {
        itemListingsRepository.deleteByTimestampIsLessThan(timestamp)
    }
}