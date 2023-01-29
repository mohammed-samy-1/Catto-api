package com.example

import io.ktor.server.application.*
import com.example.plugins.*
import com.example.room.RoomController
import com.example.routs.chatSocket
import com.example.routs.getMessages
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureKoin()
    configureSecurity()
    configureMonitoring()
    configureSockets()
    configureSerialization()
    configureRouting()
}
