package com.nimeji.gw2tp.item.adapter.out.persistence

import com.nimeji.gw2tp.item.domain.*
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ItemEntity (
    @Id
    val id: Int,

    val chatLink: String,
    val name: String,
    val icon: String?,
    @Column(length = 2048)
    val description: String?,
    val type: ItemType,
    val rarity: ItemRarity,
    val level: Int,
    val vendorValue: Int,
    val defaultSkin: Int?,
    @ElementCollection
    val flags: Set<ItemFlags>,
    @ElementCollection
    val gameTypes: Set<ItemGameTypes>,
    @ElementCollection
    val restrictions: Set<ItemRestrictions>,

    val lastBuildId: Int,
) {
    constructor(item: Item) : this(
        item.id,
        item.chatLink,
        item.name,
        item.icon,
        item.description,
        item.type,
        item.rarity,
        item.level,
        item.vendorValue,
        item.defaultSkin,
        item.flags,
        item.gameTypes,
        item.restrictions,
        123,
    )

    fun toDomain(): Item = Item(
        this.id,
        this.chatLink,
        this.name,
        this.icon,
        this.description,
        this.type,
        this.rarity,
        this.level,
        this.vendorValue,
        this.defaultSkin,
        this.flags,
        this.gameTypes,
        this.restrictions,
        this.lastBuildId
    )
}