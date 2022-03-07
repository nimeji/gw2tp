package com.nimeji.gw2tp.item.adapter.out.persistence

import com.nimeji.gw2tp.common.exceptions.ItemDoesNotExistException
import com.nimeji.gw2tp.item.adapter.out.network.ItemListingsDto
import com.nimeji.gw2tp.item.domain.*
import org.hibernate.Hibernate
import java.time.Instant
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int = -1

    @JoinColumn(name = "item_id", updatable = false, insertable = false)
    @ManyToOne
    val item: ItemEntity? = null

    constructor(
        itemListingsDto: ItemListingsDto,
        timestamp: Instant,
        priceAggregate: List<ItemListingsPriceAggregationEntity>,
    ) : this(
        itemListingsDto.id,
        itemListingsDto.buys.map { ListingEntity(it) },
        itemListingsDto.sells.map { ListingEntity(it) },
        timestamp,
        priceAggregate
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