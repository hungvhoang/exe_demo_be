package com.coffeegame.demo.controllers

import com.coffeegame.demo.dtos.UserSubmitAnswerDTO
import com.coffeegame.demo.services.topic.TopicService
import com.coffeegame.demo.services.topicanswer.UserTopicAnswerService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topic-center-svc/v1")
class TopicCenterController @Autowired constructor(
    private val userTopicAnswerService: UserTopicAnswerService,
    private val topicService: TopicService,
) {
    @PostMapping("/submit")
    @Operation(summary = "submit answer")
    fun submitAnswer(
        @RequestBody userSubmitAnswerDTO: UserSubmitAnswerDTO
    ): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userTopicAnswerService.submitAnswer(userSubmitAnswerDTO))
        } catch (e : Exception) {
            ResponseEntity.internalServerError().body(e.message)
        }
    }

    @GetMapping("/list/{id}")
    @Operation(summary = "list topic for user")
    fun listUserTopics(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(topicService.getUserTopics(id))
        } catch (e : Exception) {
            ResponseEntity.internalServerError().body(e.message)
        }
    }

    @PostMapping("/topic/{id}")
    @Operation(summary = "get topic by id")
    fun getTopicById(
        @PathVariable id: String,
        @RequestParam userId: String
    ): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(topicService.getTopic(userId = userId, topicId = id))
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body(e.message)
        }
    }
}