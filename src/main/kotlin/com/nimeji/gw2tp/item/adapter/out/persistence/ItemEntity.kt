package com.nimeji.gw2tp.item.adapter.out.persistence

import com.nimeji.gw2tp.item.domain.*
import org.hibernate.Hibernate
import javax.persistence.*

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
    @Enumerated(EnumType.STRING)
    val flags: Set<ItemFlags>,
    @ElementCollection
    @Enumerated(EnumType.STRING)
    val gameTypes: Set<ItemGameTypes>,
    @ElementCollection
    @Enumerated(EnumType.STRING)
    val restrictions: Set<ItemRestrictions>,

    val lastBuildId: Int,
) {
    interface ItemEntityId {
        fun getId(): Int
    }

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ItemEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}