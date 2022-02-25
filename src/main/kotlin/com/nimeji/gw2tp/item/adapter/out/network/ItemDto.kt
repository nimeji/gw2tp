package com.nimeji.gw2tp.item.adapter.out.network

import com.nimeji.gw2tp.item.domain.*

data class ItemDto (
    val id: Int,
    val chat_link: String,
    val name: String,
    val icon: String?,
    val description: String?,
    val type: ItemType,
    val rarity: ItemRarity,
    val level: Int,
    val vendor_value: Int,
    val default_skin: Int?,
    val flags: Set<ItemFlags>,
    val game_types: Set<ItemGameTypes>,
    val restrictions: Set<ItemRestrictions>,
    val upgrades_into: List<ItemUpgradesIntoFromDto>?,
    val upgrades_from: List<ItemUpgradesIntoFromDto>?,
) {
    fun toDomain(buildVersion: Int) = Item(
        this.id,
        this.chat_link,
        this.name,
        this.icon,
        this.description,
        this.type,
        this.rarity,
        this.level,
        this.vendor_value,
        this.default_skin,
        this.flags,
        this.game_types,
        this.restrictions,
        buildVersion
    )
}