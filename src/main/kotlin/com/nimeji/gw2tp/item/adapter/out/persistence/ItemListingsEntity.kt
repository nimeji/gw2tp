package com.nimeji.gw2tp.item.adapter.out.persistence

import com.nimeji.gw2tp.common.exceptions.ItemDoesNotExistException
import com.nimeji.gw2tp.item.adapter.out.network.ItemListingsDto
import com.nimeji.gw2tp.item.domain.Item
import com.nimeji.gw2tp.item.domain.ItemListings
import com.nimeji.gw2tp.item.domain.Listing
import java.time.Instant
import javax.persistence.*

@Entity
data class ItemListingsEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,

    @JoinColumn(name = "item_id", updatable = false, insertable = false)
    @ManyToOne
    val item: ItemEntity?,

    @Column(name = "item_id")
    val itemId: Int,
    @OrderBy("unitPrice desc")
    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    @JoinColumn(name = "itemListingsId")
    val buys: List<ListingEntity>,
    @OrderBy("unitPrice asc")
    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    @JoinColumn(name = "itemListingsId")
    val sells: List<ListingEntity>,
    val timestamp: Instant,
) {
    constructor(itemListingsDto: ItemListingsDto, timestamp: Instant) : this(
        null,
        null,
        itemListingsDto.id,
        itemListingsDto.buys.map { ListingEntity(it) },
        itemListingsDto.sells.map { ListingEntity(it) },
        timestamp,
    )

    fun toDomain(): ItemListings {
        if (item == null) {
            throw ItemDoesNotExistException()
        }

        return ItemListings(
            item.toDomain(),
            buys.map { it.toDomain() },
            sells.map { it.toDomain() },
            timestamp)
    }
}