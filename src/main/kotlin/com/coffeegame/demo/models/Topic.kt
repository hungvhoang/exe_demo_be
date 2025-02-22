package com.coffeegame.demo.models

import org.springframework.data.annotation.Id

data class Topic(
    @Id
    val topicId: String = "",

    val title: String = "",

    val description: String = "",

    val question: String = "",

    val wordRequired: Long = 150,
)
