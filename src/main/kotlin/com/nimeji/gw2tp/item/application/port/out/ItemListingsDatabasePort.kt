package com.nimeji.gw2tp.item.application.port.out

import com.nimeji.gw2tp.item.adapter.out.network.ItemListingsDto
import java.time.Instant

interface ItemListingsDatabasePort {
    fun insert(itemListingsDto: ItemListingsDto)
    fun pruneBefore(timestamp: Instant)
}