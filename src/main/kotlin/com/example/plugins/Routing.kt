package com.example.plugins

import com.example.room.RoomController
import com.example.routs.chatSocket
import com.example.routs.getMessages
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val roomController by inject<RoomController>()
    routing {
        get("/mo") {
            call.respond(HttpStatusCode.OK,"fuck")
        }
        chatSocket(roomController)
        getMessages(roomController)
    }
}
