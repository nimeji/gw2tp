package com.nimeji.gw2tp.item.application.port.`in`

interface UpdateItemDataUseCase {
    fun importItemData()
    fun isTaskRunning(): Boolean
}