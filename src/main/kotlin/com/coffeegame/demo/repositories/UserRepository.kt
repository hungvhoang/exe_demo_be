package com.coffeegame.demo.repositories

import com.coffeegame.demo.models.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface UserRepository : MongoRepository<User, String> {

    @Query(
        "{ \$and: [ " +
                " { 'fullName': { \$regex: ?0, \$options: 'i' } }, " +
                " { 'userId': { \$regex: ?1, \$options: 'i' } }, " +
                " { 'email': { \$regex: ?2, \$options: 'i' } } " +
                "] }"
    )
    fun findUserByQueryParam(
        name: String,
        userId: String,
        email: String,
    ): List<User> = listOf()

}