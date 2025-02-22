package com.coffeegame.demo.controllers

import com.coffeegame.demo.models.Topic
import com.coffeegame.demo.services.topic.TopicQueryParam
import com.coffeegame.demo.services.topic.TopicService
import com.coffeegame.demo.services.topicanswer.UserTopicAnswerQueryParam
import com.coffeegame.demo.services.topicanswer.UserTopicAnswerService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("topic-backoffice/v1")
class TopicBackOfficeController @Autowired constructor(
    private val topicService: TopicService,
    private val userTopicAnswerService: UserTopicAnswerService
) {
    @PostMapping("/topic/create")
    @Operation(summary = "create topic")
    fun createTopic(
        @RequestBody topic: Topic
    ): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(topicService.createTopic(topic))
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body(e.message)
        }
    }

    @PostMapping("/topic/create-list")
    @Operation(summary = "create topics")
    fun createTopics(
        @RequestBody topics: List<Topic>
    ): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(mapOf("success" to topicService.createList(topics)))
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body(e.message)
        }
    }

    @PostMapping("/topic/list")
    @Operation(summary = "list topics")
    fun listTopics(
        @RequestBody queryParam: TopicQueryParam
    ): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(topicService.listByQuery(queryParam))
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body(e.message)
        }
    }

    @GetMapping("/topic/{id}")
    @Operation(summary = "get topic by id")
    fun getTopicById(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(topicService.getTopicById(id))
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body(e.message)
        }
    }

    @PostMapping("/topic/answer")
    @Operation(summary = "list user answers")
    fun listUserAnswers(
        @RequestBody queryParam: UserTopicAnswerQueryParam
    ): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userTopicAnswerService.listByQuery(queryParam))
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body(e.message)
        }
    }

    @GetMapping("/topic/answer/{id}")
    @Operation(summary = "get user answer by id")
    fun getUserAnswerById(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userTopicAnswerService.getAnswerById(id))
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body(e.message)
        }
    }

    @GetMapping("/topic/answer/approve/{id}")
    @Operation(summary = "approve user answer")
    fun approveUserAnswer(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userTopicAnswerService.approveAnswer(id))
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body(e.message)
        }
    }
}