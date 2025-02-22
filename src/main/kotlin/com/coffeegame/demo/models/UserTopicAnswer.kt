package com.coffeegame.demo.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class UserTopicAnswer(

    @Id
    val answerId: String,

    @DBRef
    val user: User,

    @DBRef
    val topic: Topic,

    var status: AnswerStatus = AnswerStatus.NOT_PASS_WORD_CONDITION,

    val submittedAt: LocalDateTime = LocalDateTime.now(),

    val answerEssay: String,
)

enum class AnswerStatus {
    PASS_WORD_CONDITION, NOT_PASS_WORD_CONDITION ,APPROVED, REJECTED
}
