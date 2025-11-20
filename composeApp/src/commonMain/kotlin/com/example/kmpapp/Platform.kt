package com.example.kmpapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform