package com.nimeji.gw2tp.item.adapter.out.persistence

import com.nimeji.gw2tp.item.adapter.out.network.ListingDto
import com.nimeji.gw2tp.item.domain.Listing
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ListingEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    val listings: Int,
    val unitPrice: Int,
    val quantity: Int,
) {
    constructor(listing: ListingDto) : this(
        null,
        listing.listings,
        listing.unit_price,
        listing.quantity,
    )

    fun toDomain(): Listing {
        return Listing(listings, unitPrice, quantity)
    }
}