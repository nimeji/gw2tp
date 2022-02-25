package com.nimeji.gw2tp.item.adapter.out.network

import com.nimeji.gw2tp.item.domain.Listing

data class ListingDto (
    val listings: Int,
    val unit_price: Int,
    val quantity: Int,
) {
    fun toDomain(): Listing {
        return Listing(listings, unit_price, quantity)
    }
}