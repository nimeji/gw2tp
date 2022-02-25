package com.nimeji.gw2tp.item.domain

import java.time.Instant

data class ItemListings (
    val item: Item,
    val buys: List<Listing>,
    val sells: List<Listing>,
    val timestamp: Instant,
)