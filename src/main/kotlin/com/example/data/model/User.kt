package com.example.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
@Serializable
data class User(
    @BsonId
    val id: String = ObjectId().toString(),
    val userName: String,
    val email: String,
    val password: String,
    val isOnline: Boolean = false
)
