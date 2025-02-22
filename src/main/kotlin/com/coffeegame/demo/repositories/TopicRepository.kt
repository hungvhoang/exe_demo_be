package com.coffeegame.demo.repositories

import com.coffeegame.demo.models.Topic
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface TopicRepository : MongoRepository<Topic, String> {

    @Query(
        "{ \$and: [ " +
                " { 'title': { \$regex: ?0, \$options: 'i' } }, " +
                " { 'description': { \$regex: ?1, \$options: 'i' } }, " +
                " { 'question': { \$regex: ?2, \$options: 'i' } } " +
                "] }"
    )
    fun findByQueryParam(
        title: String,
        description: String,
        question: String,
    ): List<Topic> = listOf()
}