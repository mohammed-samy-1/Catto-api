package com.example.room

import io.ktor.websocket.*
import javax.print.attribute.standard.JobOriginatingUserName

data class Member(
    val userName: String,
    val sessionId: String,
    val websocket:WebSocketSession
)
