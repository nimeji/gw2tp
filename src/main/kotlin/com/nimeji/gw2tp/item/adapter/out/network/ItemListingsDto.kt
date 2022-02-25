package com.nimeji.gw2tp.item.adapter.out.network

data class ItemListingsDto (
    val id: Int,
    val buys: List<ListingDto>,
    val sells: List<ListingDto>,
)