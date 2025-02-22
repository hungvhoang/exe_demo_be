package com.coffeegame.demo.repositories

import com.coffeegame.demo.models.UserTopicAnswer
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface UserTopicAnswerRepository: MongoRepository<UserTopicAnswer, String> {

    fun findAllByUser_UserId(userId: String): List<UserTopicAnswer>

    fun findAllByTopic_TopicId(topicId: String): List<UserTopicAnswer>

    fun findAllByTopic_TopicIdAndUser_UserId(topicId: String, userId: String): List<UserTopicAnswer>

    @Query(
        "{ \$and: [ " +
                " { 'answerId': { \$regex: ?0, \$options: 'i' } }, " +
                " { 'userId': { \$regex: ?1, \$options: 'i' } }, " +
                " { 'topicId': { \$regex: ?2, \$options: 'i' } } " +
                "] }"
    )
    fun findByQueryParam (
        answerId: String,
        userId: String,
        topicId: String,
    ) : List<UserTopicAnswer>
}