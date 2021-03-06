package com.nimeji.gw2tp.item.adapter.out.persistence

import com.nimeji.gw2tp.item.adapter.out.network.ListingDto
import com.nimeji.gw2tp.item.domain.Listing
import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ListingEntity (
    val listings: Int,
    val unitPrice: Int,
    val quantity: Int,
) {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    val id: Int? = null

    constructor(listingEntityDto: ListingDto) : this(
        listingEntityDto.listings,
        listingEntityDto.unit_price,
        listingEntityDto.quantity,
    )

    fun toDomain(): Listing {
        return Listing(listings, unitPrice, quantity)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ListingEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}