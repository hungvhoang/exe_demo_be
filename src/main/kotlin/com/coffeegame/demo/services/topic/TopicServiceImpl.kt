package com.coffeegame.demo.services.topic

import com.coffeegame.demo.models.Topic
import com.coffeegame.demo.repositories.TopicRepository
import com.coffeegame.demo.repositories.UserTopicAnswerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TopicServiceImpl @Autowired constructor(
    private val topicRepository: TopicRepository,
    private val userTopicAnswerRepository: UserTopicAnswerRepository,
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

    override fun getUserTopics(userId: String): List<Topic> {
        val userAnswers = userTopicAnswerRepository.findAllByUser_UserId(userId)
        val topicList = topicRepository.findAll()
        return topicList.filter { topic ->
            userAnswers.none { it.topic.topicId == topic.topicId }
        }.shuffled().take(3)
    }

    override fun getTopic(userId: String, topicId: String): Topic {

        val userTopicAnswerRepository = userTopicAnswerRepository
            .findAllByTopic_TopicIdAndUser_UserId(topicId, userId)

        if(userTopicAnswerRepository.isNotEmpty()) {
            throw IllegalArgumentException("User already answered this topic")
        }

        return topicRepository.findById(topicId).orElse(null)
    }

    override fun createList(topics: List<Topic>): Boolean {
        topicRepository.saveAll(topics)
        return true
    }
}