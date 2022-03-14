package com.nimeji.gw2tp.item.adapter.`in`.web

import com.nimeji.gw2tp.common.exceptions.TaskRunningException
import com.nimeji.gw2tp.item.application.port.`in`.GetItemsUseCase
import com.nimeji.gw2tp.item.application.port.`in`.UpdateItemDataUseCase
import com.nimeji.gw2tp.item.domain.Item
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("items")
class ItemController(
    @Autowired private val getItemsUseCase: GetItemsUseCase,
    @Autowired private val rebuildItemDataUseCase: UpdateItemDataUseCase,
) {
    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    fun getItems(): List<Item> {
        return getItemsUseCase.getItems()
    }

    @PostMapping("rebuild")
    //@PreAuthorize("hasRole('ADMIN')")
    fun rebuildItemData() {
        if (rebuildItemDataUseCase.isTaskRunning()) {
            throw TaskRunningException("rebuild task is already running")
        }
        rebuildItemDataUseCase.importItemData()
    }
}