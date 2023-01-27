package com.example.room

import MessageDataSource
import com.example.data.model.Message
import io.ktor.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap

class RoomController(
    private val messageDataSource: MessageDataSource
) {
    private val members = ConcurrentHashMap<String, Member>()
    fun joinRoom(
        userName: String,
        sessionId: String,
        session: WebSocketSession
    ) {
        if (members.contains(userName)) throw MemberExistsException()
        else members[userName] = Member(
            userName = userName,
            sessionId = sessionId,
            websocket = session
        )
    }
    suspend fun sendMessage(sender:String , message:String){
        members.values.forEach { member ->
            val messageEntity = Message(message, sender ,System.currentTimeMillis())
            messageDataSource.insertMessage(messageEntity)
            val parseMessage = Json.encodeToString(messageEntity)
            member.websocket.send(Frame.Text(parseMessage))
        }
    }
    suspend fun getAllMessages() : List<Message>{
        return messageDataSource.getAllMessages()
    }
    suspend fun disConnect(userName: String){
        members[userName]?.websocket?.close()
        if (members.contains(userName)){
            members.remove(userName)
        }
    }
}