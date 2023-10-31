package com.example.demo.repository.impl

import com.example.demo.model.Message
import com.example.demo.repository.MessageRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.List

@Component
class MessageRepositoryJDBC(val db: JdbcTemplate) : MessageRepository {

    override fun <S : Message> save(entity: S): S {
        val id = entity.id ?: UUID.randomUUID().toString()
        db.update("insert into messages values ( ?, ? )",
                id , entity.text)
        return entity
    }

    override fun findAll(): MutableIterable<Message> {
        return db.query("select * from messages") { response, _ ->
            Message(response.getString("id"), response.getString("text"))}
    }

    override fun <S : Message?> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        TODO("Not yet implemented")
    }

    override fun findById(id: String): Optional<Message> {
        val result : List<Message> = db.query("select * from messages where id = ?", id) { response, _ ->
            Message(response.getString("id"), response.getString("text"))}
        return if (result.isEmpty()) Optional.empty() else Optional.of(result.first())
    }

    override fun existsById(id: String): Boolean {
        TODO("Not yet implemented")
    }



    override fun findAllById(ids: MutableIterable<String>): MutableIterable<Message> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: String) {
        TODO("Not yet implemented")
    }

    override fun delete(entity: Message) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<String>) {
        TODO("Not yet implemented CEF")
    }

    override fun deleteAll(entities: MutableIterable<Message>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}