package com.nimeji.gw2tp.item.application.port.out

import com.nimeji.gw2tp.item.adapter.out.network.ItemListingsDto
import com.nimeji.gw2tp.item.domain.Percentile
import java.time.Instant

interface ItemListingsDatabasePort {
    fun insert(
        itemListingsDto: ItemListingsDto,
        buyAggregates: Map<Percentile, Int>,
        sellAggregates: Map<Percentile, Int>
    )
    fun pruneBefore(timestamp: Instant)
}