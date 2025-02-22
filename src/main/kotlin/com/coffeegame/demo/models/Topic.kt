package com.coffeegame.demo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Topic(

    @Id
    val topicId: String = "",

    val title: String = "",

    val description: String = "",

    val question: String = "",

    val wordRequired: Long = 150,
)
