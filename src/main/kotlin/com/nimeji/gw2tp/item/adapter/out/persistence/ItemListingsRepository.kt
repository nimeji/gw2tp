package com.nimeji.gw2tp.item.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.Instant
import java.util.UUID

@Repository
interface ItemListingsRepository: CrudRepository<ItemListingsEntity, UUID>, JpaRepository<ItemListingsEntity, UUID> {
    fun deleteByTimestampIsLessThan(timestamp: Instant)
}