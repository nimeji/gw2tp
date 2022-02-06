package com.nimeji.gw2tp.item.domain

class Item (
    val id: Int,
    val chatLink: String,
    val name: String,
    val icon: String?,
    val description: String?,
    val type: ItemType,
    val rarity: ItemRarity,
    val level: Int,
    val vendorValue: Int,
    val defaultSkin: Int?,
    val flags: Set<ItemFlags>,
    val gameTypes: Set<ItemGameTypes>,
    val restrictions: Set<ItemRestrictions>,

    val lastBuildId: Int,
) {
    class ItemId (val value: Int)
}