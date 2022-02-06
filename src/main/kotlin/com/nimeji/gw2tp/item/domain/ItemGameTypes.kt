package com.nimeji.gw2tp.item.domain

import com.fasterxml.jackson.annotation.JsonValue

enum class ItemGameTypes(@JsonValue val value: String) {
    ACTIVITY("Activity"),
    DUNGEON("Dungeon"),
    PVE("Pve"),
    PVP("Pvp"),
    PVP_LOBBY("PvpLobby"),
    WVW("Wvw"),
}