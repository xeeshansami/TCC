package com.paxees.tcc.models

enum class DrawerItem {
    PEP, FATCA, CRS, HEADER_PROCEEDING, HEADER_DISABLED, ITEM_DONE, ITEM_LINEUP
}

data class DrawerModel(var title: String?, var item: DrawerItem?)
