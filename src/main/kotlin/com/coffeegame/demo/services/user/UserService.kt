package com.coffeegame.demo.services.user

import com.coffeegame.demo.models.User

interface UserService {
    fun createUser(user: User): User
    fun getUserById(id: String): User?
    fun updateUser(id: String, user: User): User
    fun deleteUserById(id: String): Boolean
    fun findUserByQueryParam(userQueryParam: UserQueryParam): List<User>
}

data class UserQueryParam(
    val name: String = "",
    val userId: String = "",
    val email: String = ""
)