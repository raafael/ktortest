package br.com.ktortest.route

import br.com.ktortest.service.UserService
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.user(userService: UserService) {

    get("/user/all") {
        call.respond(userService.findAll())
    }

    get("/user/all/address") {
        call.respond(userService.findAllWithAddress())
    }
}

