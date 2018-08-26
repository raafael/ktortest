package br.com.ktortest.service

import br.com.ktortest.model.User

interface UserService {
    fun findAll() : List<User>
    fun findAllWithAddress() : List<User>
}