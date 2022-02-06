package com.nimeji.gw2tp.item.adapter.out.persistence

import com.nimeji.gw2tp.item.adapter.out.persistence.ItemEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository: CrudRepository<ItemEntity, Int>