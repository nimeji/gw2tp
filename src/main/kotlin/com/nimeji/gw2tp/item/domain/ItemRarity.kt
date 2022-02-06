package com.nimeji.gw2tp.item.domain

import com.fasterxml.jackson.annotation.JsonValue

enum class ItemRarity(@JsonValue val value: String) {
    JUNK("Junk"),
    BASIC("Basic"),
    FINE("Fine"),
    MASTERWORK("Masterwork"),
    RARE("Rare"),
    EXOTIC("Exotic"),
    ASCENDED("Ascended"),
    LEGENDARY("Legendary"),
}