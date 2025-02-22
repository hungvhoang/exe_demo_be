package com.coffeegame.demo.dtos

data class UserSubmitAnswerDTO(
    val userId: String,
    val topicId: String,
    val answer: String,
)
