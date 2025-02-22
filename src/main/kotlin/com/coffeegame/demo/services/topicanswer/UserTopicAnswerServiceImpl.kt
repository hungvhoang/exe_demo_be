package com.coffeegame.demo.services.topicanswer

import com.coffeegame.demo.dtos.UserSubmitAnswerDTO
import com.coffeegame.demo.models.AnswerStatus
import com.coffeegame.demo.models.UserTopicAnswer
import com.coffeegame.demo.repositories.TopicRepository
import com.coffeegame.demo.repositories.UserRepository
import com.coffeegame.demo.repositories.UserTopicAnswerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserTopicAnswerServiceImpl @Autowired constructor(
    private val userTopicAnswerRepository: UserTopicAnswerRepository,
    private val userRepository: UserRepository,
    private val topicRepository: TopicRepository
): UserTopicAnswerService {

    override fun submitAnswer(dto: UserSubmitAnswerDTO): UserTopicAnswer {
        val user = userRepository.findById(dto.userId).orElse(null)
        val topic = topicRepository.findById(dto.topicId).orElse(null)

        if (user == null || topic == null) {
            throw IllegalArgumentException("User or Topic not found")
        }

        val answer = UserTopicAnswer(
            answerId = UUID.randomUUID().toString(),
            user = user,
            topic = topic,
            answerEssay = dto.answer,
            status = if (dto.answer.trim().length < topic.wordRequired) {
                AnswerStatus.NOT_PASS_WORD_CONDITION
            } else {
                AnswerStatus.PASS_WORD_CONDITION
            }
        )

        return userTopicAnswerRepository.save(answer)
    }
//    override fun createAnswer(answer: UserTopicAnswer): UserTopicAnswer {
//        return userTopicAnswerRepository.save(answer)
//    }

    override fun getAnswerById(id: String): UserTopicAnswer {
        return userTopicAnswerRepository.findById(id).orElse(null)
    }

    override fun updateAnswer(id: String, updatedAnswer: UserTopicAnswer) {
        TODO("Not yet implemented")
    }

    override fun approveAnswer(id: String): Boolean {
        val answer = userTopicAnswerRepository.findById(id).orElse(null)
        if (answer == null) {
            throw IllegalArgumentException("Answer not found")
        }

        answer.status = AnswerStatus.APPROVED
        userTopicAnswerRepository.save(answer)
        return true
    }

    override fun deleteAnswerById(id: String) {
        TODO("Not yet implemented")
    }

    override fun listByQuery(query: UserTopicAnswerQueryParam): List<UserTopicAnswer> {
        return userTopicAnswerRepository.findAll(Sort.by("submittedAt")).filter(query)
    }

    private fun List<UserTopicAnswer>.filter(query: UserTopicAnswerQueryParam): List<UserTopicAnswer> {
        return this.filter { answer ->
            (query.answerStatuses.isEmpty() || answer.status in query.answerStatuses) &&
                    (query.userName.isEmpty() || answer.user.fullName.contains(query.userName, ignoreCase = true)) &&
                    (query.question.isEmpty() || answer.topic.question.contains(query.question, ignoreCase = true)) &&
                    (query.title.isEmpty() || answer.topic.title.contains(query.title, ignoreCase = true)) &&
                    (query.answerId.isEmpty() || answer.answerId.contains(query.answerId, ignoreCase = true)) &&
                    (query.userId.isEmpty() || answer.user.userId.contains(query.userId, ignoreCase = true)) &&
                    (query.topicId.isEmpty() || answer.topic.topicId.contains(query.topicId, ignoreCase = true))
        }

    }

}