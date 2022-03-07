package com.nimeji.gw2tp.item.domain

import com.fasterxml.jackson.annotation.JsonValue

enum class ItemType(@JsonValue val value: String) {
    ARMOR("Armor"),
    BACK("Back"),
    BAG("Bag"),
    CONSUMABLE("Consumable"),
    CONTAINER("Container"),
    CRAFTING_MATERIAL("CraftingMaterial"),
    GATHERING("Gathering"),
    GIZMO("Gizmo"),
    KEY("Key"),
    MINI_PET("MiniPet"),
    TOOL("Tool"),
    TRAIT("Trait"),
    TRINKET("Trinket"),
    TROPHY("Trophy"),
    UPGRADE_COMPONENT("UpgradeComponent"),
    WEAPON("Weapon"),
    QUUX("Quux"),
    QUX("Qux"),
}