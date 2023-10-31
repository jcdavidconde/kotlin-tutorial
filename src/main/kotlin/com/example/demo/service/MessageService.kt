package com.example.demo.service

import com.example.demo.model.Message
import com.example.demo.repository.MessageRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageService(val messageRepositoryJDBC: MessageRepository) {

    fun findMessages(): List<Message> = messageRepositoryJDBC.findAll().toList()

    fun findMessageById(id: String): List<Message> = messageRepositoryJDBC.findById(id).toList()

    //This is fun save
    fun save(message: Message) {
        messageRepositoryJDBC.save(message)
    }

    fun <T : Any> Optional<out T>.toList(): List<T> =
            if (isPresent) listOf(get()) else emptyList()

}