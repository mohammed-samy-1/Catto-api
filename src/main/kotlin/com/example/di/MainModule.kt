package com.example.di

import MessageDataSource
import com.example.data.MessageDataSourceImplementation
import com.example.room.RoomController
import org.koin.core.module.Module
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val MainModule = module {
    single {
        KMongo
            .createClient()
            .coroutine
            .getDatabase("message_db")
    }
    single <MessageDataSource> {
        MessageDataSourceImplementation(get())
    }
    single {
        RoomController(get())
    }
}