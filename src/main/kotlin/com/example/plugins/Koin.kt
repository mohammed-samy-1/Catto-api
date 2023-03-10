package com.example.plugins

import com.example.di.MainModule
import com.example.module
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureKoin(){
    install(Koin){
        modules(MainModule)
    }
}