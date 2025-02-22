package com.coffeegame.demo.controllers

import com.coffeegame.demo.models.User
import com.coffeegame.demo.services.user.UserQueryParam
import com.coffeegame.demo.services.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user-service/v1")
class UserController @Autowired constructor(
    val userService: UserService
) {
    @PostMapping("/create")
    fun createUser(
        @RequestBody user: User
    ): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userService.createUser(user))
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body(e.message)
        }
    }


    @GetMapping("/get/{id}")
    fun getUserById(
        @PathVariable id: String
    ): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userService.getUserById(id))
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body(e.message)
        }
    }

    @PostMapping("/list")
    fun listUsers(
        @RequestBody userQueryParam: UserQueryParam
    ): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(userService.findUserByQueryParam(userQueryParam))
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body(e.message)
        }
    }

    fun updateUser() {
        TODO()
    }

    fun deleteUser() {
        TODO()
    }
}