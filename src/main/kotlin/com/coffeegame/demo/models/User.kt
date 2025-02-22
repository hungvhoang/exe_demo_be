package com.coffeegame.demo.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(

    @Id
    val userId: String, //use phone number ass userId
    val fullName: String,
    val birthYear: Int = 0,
    val email: String = "",
    var credit: Int = 0,
)