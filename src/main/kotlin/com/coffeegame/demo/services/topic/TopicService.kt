package com.coffeegame.demo.services.topic

import com.coffeegame.demo.models.Topic

interface TopicService {

    fun createTopic(topic: Topic): Topic

    fun getTopicById(id: String): Topic?

    fun updateTopic(id: String, updatedTopic: Topic)

    fun deleteTopicById(id: String)

    fun listByQuery(query: TopicQueryParam): List<Topic>

    fun getUserTopics(userId: String): List<Topic>

    fun getTopic(userId: String, topicId: String): Topic

    fun createList(topics: List<Topic>): Boolean
}

data class TopicQueryParam(
    val title: String = "",
    val description: String = "",
    val question: String = "",
)