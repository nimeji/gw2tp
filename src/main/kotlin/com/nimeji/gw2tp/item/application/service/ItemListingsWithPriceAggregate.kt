package com.nimeji.gw2tp.item.application.service

import com.nimeji.gw2tp.item.adapter.out.network.ListingDto
import com.nimeji.gw2tp.item.domain.ItemListingsPriceAggregateList

data class ItemListingsWithPriceAggregate (
    val id: Int,
    val buys: List<ListingDto>,
    val sells: List<ListingDto>,
    val priceAggregates: ItemListingsPriceAggregateList,
)