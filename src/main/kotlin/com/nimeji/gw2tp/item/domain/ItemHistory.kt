package com.nimeji.gw2tp.item.domain

class ItemHistory (private val history: MutableList<Listings>) {
    fun addListings(listings: Listings) {
        history.add(listings)
    }

    fun getLatest(): Listings? {
        return history.maxByOrNull {
            it.timestamp
        }
    }
}