package com.nimeji.gw2tp.item.application.port.`in`

interface RebuildItemDataUseCase {
    fun rebuildItemData()
    fun isTaskRunning(): Boolean
}