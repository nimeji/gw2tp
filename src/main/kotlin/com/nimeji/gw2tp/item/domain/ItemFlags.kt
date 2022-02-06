package com.nimeji.gw2tp.item.domain

import com.fasterxml.jackson.annotation.JsonValue

enum class ItemFlags(@JsonValue val value: String) {
    ACCOUNT_BIND_ON_USE("AccountBindOnUse"),
    ACCOUNT_BOUND("AccountBound"),
    ATTUNED("Attuned"),
    BULK_CONSUME("BulkConsume"),
    DELETE_WARNING("DeleteWarning"),
    HIDE_SUFFIX("HideSuffix"),
    INFUSED("Infused"),
    MONSTER_ONLY("MonsterOnly"),
    NO_MYSTIC_FORGE("NoMysticForge"),
    NO_SALVAGE("NoSalvage"),
    NO_SELL("NoSell"),
    NOT_UPGRADEABLE("NotUpgradeable"),
    NO_UNDERWATER("NoUnderwater"),
    SOUL_BIND_ON_ACQUIRE("SoulbindOnAcquire"),
    SOUL_BIND_ON_USE("SoulBindOnUse"),
    TONIC("Tonic"),
    UNIQUE("Unique"),
}