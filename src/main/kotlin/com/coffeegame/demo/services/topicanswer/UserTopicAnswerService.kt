package com.coffeegame.demo.services.topicanswer

import com.coffeegame.demo.dtos.UserSubmitAnswerDTO
import com.coffeegame.demo.models.AnswerStatus
import com.coffeegame.demo.models.UserTopicAnswer

interface UserTopicAnswerService {
//    fun createAnswer(answer: UserTopicAnswer): UserTopicAnswer
    fun submitAnswer(dto: UserSubmitAnswerDTO): UserTopicAnswer
    fun getAnswerById(id: String): UserTopicAnswer
    fun updateAnswer(id: String, updatedAnswer: UserTopicAnswer)
    fun approveAnswer(id: String): Boolean
    fun deleteAnswerById(id: String)
    fun listByQuery(query: UserTopicAnswerQueryParam): List<UserTopicAnswer>
}

data class UserTopicAnswerQueryParam(
    var answerId: String = "",
    var userId: String = "",
    var topicId: String = "",
    var answerStatuses: List<AnswerStatus> = emptyList(),
    var userName: String = "",
    var question: String = "",
    var title: String = "",
    var sortBy: String = "submittedAt",
)