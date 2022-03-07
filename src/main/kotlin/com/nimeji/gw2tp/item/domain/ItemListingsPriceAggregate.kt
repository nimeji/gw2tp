package com.nimeji.gw2tp.item.domain

data class ItemListingsPriceAggregate (
    val percentile: Percentile,
    val buy: Int?,
    val sell: Int?,
)