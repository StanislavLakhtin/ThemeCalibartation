package me.lakhtin.themecalibration

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform