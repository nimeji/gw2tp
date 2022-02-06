package com.nimeji.gw2tp.item.application.port.`in`

import com.nimeji.gw2tp.item.domain.Item
import com.nimeji.gw2tp.item.domain.Listing
import java.time.Instant

data class AddItemListingsDatapointCommand(
    val itemId: Item.ItemId,
    val buys: List<Listing>,
    val sells: List<Listing>,
    val timestamp: Instant,
)