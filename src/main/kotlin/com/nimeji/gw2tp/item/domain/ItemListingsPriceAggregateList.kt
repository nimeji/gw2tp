package com.nimeji.gw2tp.item.domain

data class ItemListingsPriceAggregateList (
    val aggregates: List<ItemListingsPriceAggregate>
) {
    fun getPercentile(percentile: Percentile): ItemListingsPriceAggregate? {
        return aggregates.find { it.percentile == percentile }
    }
}