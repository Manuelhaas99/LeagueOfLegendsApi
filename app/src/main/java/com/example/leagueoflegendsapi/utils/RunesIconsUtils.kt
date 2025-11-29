package com.example.leagueoflegendsapi.utils

fun runesIconsUtil(runeId: Int): String {
    val baseUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles"
    val path = when(runeId) {
        8005 -> "precision/presstheattack/presstheattack.png" // Press the Attack
        8008 -> "precision/lethaltempo/lethaltempo.png" // Lethal Tempo
        8021 -> "precision/fleetfootwork/fleetfootwork.png" // Fleet Footwork
        8010 -> "precision/conqueror/conqueror.png" // Conqueror

        // Dominación (8100)
        8112 -> "domination/electrocute/electrocute.png" // Electrocute
        8128 -> "domination/darkharvest/darkharvest.png" // Dark Harvest
        9923 -> "domination/hailofblades/hailofblades.png" // Hail of Blades

        // Brujería (8200)
        8214 -> "sorcery/summoneraery/summoneraery.png" // Aery
        8229 -> "sorcery/arcanecomet/arcanecomet.png" // Arcane Comet
        8230 -> "sorcery/phaserush/phaserush.png" // Phase Rush

        // Valor (8400)
        8437 -> "resolve/graspofintheundying/graspoftheundying.png" // Grasp
        8439 -> "resolve/veteranaftershock/veteranaftershock.png" // Aftershock
        8465 -> "resolve/guardian/guardian.png" // Guardian

        // Inspiración (8300)
        8351 -> "7203_whimsy/glacialaugment/glacialaugment.png" // Glacial
        8360 -> "7203_whimsy/unsealedspellbook/unsealedspellbook.png" // Spellbook
        8369 -> "7203_whimsy/firststrike/firststrike.png" // First Strike

        else -> "runesicon.png"
    }
    return "$baseUrl/$path"
}

fun getRuneStyleIconUrl(styleId: Int): String {
    val styleName = when (styleId) {
        8000 -> "7201_precision"
        8100 -> "7200_domination"
        8200 -> "7202_sorcery"
        8300 -> "7203_whimsy"
        8400 -> "7204_resolve"
        else -> "7201_precision"
    }
    return "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perk-images/styles/$styleName.png"
}