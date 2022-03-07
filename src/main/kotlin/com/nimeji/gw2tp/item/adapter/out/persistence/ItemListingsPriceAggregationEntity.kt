package com.nimeji.gw2tp.item.adapter.out.persistence

import com.nimeji.gw2tp.item.domain.ItemListingsPriceAggregate
import com.nimeji.gw2tp.item.domain.Percentile
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
data class ItemListingsPriceAggregationEntity (
    @Enumerated(EnumType.STRING)
    val percentile: Percentile,
    val buy: Int?,
    val sell: Int?,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int = -1

    fun toDomain(): ItemListingsPriceAggregate {
        return ItemListingsPriceAggregate(percentile, buy, sell)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ItemListingsPriceAggregationEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}