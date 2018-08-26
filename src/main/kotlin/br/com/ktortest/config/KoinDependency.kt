package br.com.ktortest.config

import br.com.ktortest.dao.UserRepository
import br.com.ktortest.service.UserService
import br.com.ktortest.service.UserServiceImpl
import org.koin.dsl.module.applicationContext

val userAppModule  = applicationContext {
    bean { UserServiceImpl(get()) as UserService } // get() Will resolve UserRepository
    bean { UserRepository() }
}