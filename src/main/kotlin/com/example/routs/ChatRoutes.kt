package com.example.routs

import com.example.room.MemberExistsException
import com.example.room.RoomController
import com.example.sessions.ChatSession
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach

fun Route.chatSocket(roomController: RoomController) {
    webSocket("/chat-socket") {
        val session = call.sessions.get<ChatSession>()
        if (session == null) {
            close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "no socket"))
            return@webSocket
        }
        incoming.consumeEach {
            if (it is Frame.Text) {
                roomController.sendMessage(sender = session.name, it.readText())
            }
        }
        try {
            roomController.joinRoom(session.name, session.sessionId, this)
        } catch (e: MemberExistsException) {
            call.respond(e.localizedMessage)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            roomController.disConnect(session.name)
        }
    }
}

fun Route.getMessages(roomController: RoomController) {
    get("/messages") {
        call.respond(
            HttpStatusCode.OK,
            roomController.getAllMessages()
        )
    }
}