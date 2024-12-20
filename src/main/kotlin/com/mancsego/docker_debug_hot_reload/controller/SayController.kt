package com.mancsego.docker_debug_hot_reload.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SayController {
    @GetMapping("/say")
    fun say (@RequestParam(required = false) what: String) :String {
        if (what.isBlank()) return "Hello!"

        return "Hello $what!"
    }
}