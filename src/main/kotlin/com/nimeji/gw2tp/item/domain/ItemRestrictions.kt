package com.nimeji.gw2tp.item.domain

import com.fasterxml.jackson.annotation.JsonValue

enum class ItemRestrictions(@JsonValue val value: String) {
    ASURA("Asura"),
    CHARR("Charr"),
    FEMALE("Female"),
    HUMAN("Human"),
    NORN("Norn"),
    SYLVARI("Sylvari"),
    ELEMENTALIST("Elementalist"),
    ENGINEER("Engineer"),
    GUARDIAN("Guardian"),
    MESMER("Mesmer"),
    NECROMANCER("Necromancer"),
    RANGER("Ranger"),
    THIEF("Thief"),
    WARRIOR("Warrior"),
}