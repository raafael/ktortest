package br.com.ktortest.service

import br.com.ktortest.dao.UserRepository

class UserServiceImpl(val userRepository: UserRepository) : UserService {

   override fun findAll() = userRepository.findAll()

    override fun findAllWithAddress() = userRepository.findAllWithAddress()
}