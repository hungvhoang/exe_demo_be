package com.coffeegame.demo.services.topic

import com.coffeegame.demo.models.Topic

interface TopicService {

    fun createTopic(topic: Topic): Topic

    fun getTopicById(id: String): Topic?

    fun updateTopic(id: String, updatedTopic: Topic)

    fun deleteTopicById(id: String)

    fun listByQuery(query: TopicQueryParam): List<Topic>
}

data class TopicQueryParam(
    val title: String = "",
    val description: String = "",
    val question: String = "",
)