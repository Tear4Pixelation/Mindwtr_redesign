package com.gipflig.mindwtr_redesign

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform