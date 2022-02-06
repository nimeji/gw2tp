package com.nimeji.gw2tp.item.domain

import java.time.Instant

class Listings(private val buys: List<Listing>, private val sells: List<Listing>, val timestamp: Instant) {
    fun currentBuyPrice(): Int {
        return buys.minOf { it.unitPrice }
    }

    fun currentSellPrice(): Int {
        return sells.minOf { it.unitPrice }
    }
}