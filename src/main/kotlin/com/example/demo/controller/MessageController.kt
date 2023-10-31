package com.example.demo.controller

import com.example.demo.model.Message
import com.example.demo.service.MessageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(val messageService: MessageService) {

    @GetMapping("/")
    fun index(@RequestParam("name") name: String) = "Hello, ${name.uppercase()}!"

    @GetMapping("/message")
    fun message() = listOf(
            Message("1","Hello"),
            Message("2","Bonjour"),
            Message("3","Privet!")
    )

    @GetMapping("/message/list")
    fun list(): List<Message> = messageService.findMessages()

    @PostMapping("/message")
    fun post(@RequestBody message: Message) = messageService.save(message)

    @GetMapping("/message/{id}")
    fun findById(@PathVariable id: String) = messageService.findMessageById(id)

}