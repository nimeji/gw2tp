package com.nimeji.gw2tp.item.adapter.out.persistence

import com.nimeji.gw2tp.common.exceptions.ItemDoesNotExistException
import com.nimeji.gw2tp.item.adapter.out.network.ItemListingsDto
import com.nimeji.gw2tp.item.application.service.ItemListingsWithPriceAggregate
import com.nimeji.gw2tp.item.domain.*
import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.time.Instant
import java.util.UUID
import javax.persistence.*

@Entity
data class ItemListingsEntity (
    @Column(name = "item_id")
    val itemId: Int,
    @OrderBy("unitPrice desc")
    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    val buys: List<ListingEntity>,
    @OrderBy("unitPrice asc")
    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val sells: List<ListingEntity>,
    val timestamp: Instant,

    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    @JoinColumn(name = "itemListingsId")
    val priceAggregates: List<ItemListingsPriceAggregationEntity> = listOf()
) {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    val id: Int? = null

    @JoinColumn(name = "item_id", updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val item: ItemEntity? = null

    constructor(itemListings: ItemListingsWithPriceAggregate, timestamp: Instant) : this(
        itemListings.id,
        itemListings.buys.map { ListingEntity(it) },
        itemListings.sells.map { ListingEntity(it) },
        timestamp,
        itemListings.priceAggregates.aggregates.map { ItemListingsPriceAggregationEntity(it) }
    )

    fun toDomain(): ItemListings {
        return ItemListings(
            item?.toDomain() ?: throw ItemDoesNotExistException(),
            buys.map { it.toDomain() },
            sells.map { it.toDomain() },
            timestamp,
            ItemListingsPriceAggregateList(priceAggregates.map { it.toDomain() })
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ItemListingsEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}