package com.nimeji.gw2tp.item.application.port.out

import com.nimeji.gw2tp.item.application.service.ItemListingsWithPriceAggregate
import java.time.Instant

interface ItemListingsDatabasePort {
    fun insertAll(itemListings: List<ItemListingsWithPriceAggregate>)
    fun pruneBefore(timestamp: Instant)
}