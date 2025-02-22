package com.coffeegame.demo.services.user

import com.coffeegame.demo.models.User
import com.coffeegame.demo.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl @Autowired constructor (
    private val userRepository: UserRepository
) : UserService {
    override fun createUser(user: User): User {
        return userRepository.save(user)
    }

    override fun getUserById(id: String): User? {
        return userRepository.findById(id).orElse(null)
    }

    override fun updateUser(id: String, user: User): User {
        return userRepository.save(user)
    }

    override fun deleteUserById(id: String): Boolean {
        userRepository.deleteById(id)
        return true
    }

    override fun findUserByQueryParam(userQueryParam: UserQueryParam): List<User> {
        return userRepository.findUserByQueryParam(userQueryParam.name, userQueryParam.userId, userQueryParam.email)
    }
}