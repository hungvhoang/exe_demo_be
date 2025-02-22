package com.coffeegame.demo.services.topic

import com.coffeegame.demo.models.Topic
import com.coffeegame.demo.repositories.TopicRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TopicServiceImpl @Autowired constructor(
    private val topicRepository: TopicRepository
) : TopicService {
    override fun createTopic(topic: Topic): Topic {
        return topicRepository.save(topic)
    }

    override fun getTopicById(id: String): Topic? {
        return topicRepository.findById(id).orElse(null)
    }

    override fun updateTopic(id: String, updatedTopic: Topic) {
        topicRepository.save(updatedTopic)
    }

    override fun deleteTopicById(id: String) {
        TODO("Not yet implemented")
    }

    override fun listByQuery(query: TopicQueryParam): List<Topic> {
        return topicRepository.findByQueryParam(query.title, query.description, query.question)
    }
}